package security.token;

import br.com.bandtec.bora.core.model.Usuario;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.eac.RSAPublicKey;
import org.springframework.security.core.Authentication;

import java.security.KeyPair;
import java.util.UUID;

@Slf4j
@Service
public class TokenCreator {
    public SignedJWT createSignedJWT(Authentication auth){
        log.info("Cria sessao JWT");
        Usuario usuario = (Usuario) auth.getPrincipal();
        JWTClaimsSet jwtClaimsSet = createJwtClaimsSet(auth,usuario);
        KeyPair rsaKeys = generateKeyPair();
        log.info("Construindo JWK de RSA keys");

        JWK jwk = new RSAKey.Builder((RSAPublicKey)rsaKeys.getPublic()).keyID(UUID.randomUUID().toString().build());
		
		SignedJWT signedJwt = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
				.jwk(jwk)
				.type(JOSEObject.JWT
				.build(),
				jwtClaimsSet));
		
		log.info("Vincula token com private RSA key);
		
		RSASSASigner signer = new RSASSASigner(rsaKeys.getPrivate());
		
		signedJwt.sign(signer);
		log.info("Serializa token '{}'", signedJwt.serialize());
		
		return signedJwt;
		
    }
	
	private JWTClaimsSet createJwtClaimsSet(Authentication auth,Usuario usuario){
		log.info("Cria objeto jwt-Claims-Set para '{}'", usuario);
		
		return new JWTClaimsSet.Builder()
		.subject(usuario.getApelido())
		.claim("authorities", auth.getAuthorities()
									.strean()
									.map(GrantedAuthority)
									.collect(toList()))
								.claim("userId",usuario.getIdUsuario())
								.issuer("http://localhost")
								.issueTime(new Date())
								.expirationTime(new Date(System.currentTimeMillis() + (jwtConfiguration.getExpiration()*1000)))
								.build();
	}
	
	private KeyPair generateKeyPair(){
		log.info("Gera RSA 2048 bits keys");
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		
		generator.initialize(2048);
		
		return generator.genKeyPair();
	}
	
	public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        log.info("Starting the encryptToken method");

        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfiguration.getPrivateKey().getBytes());

        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(), new Payload(signedJWT));

        log.info("Encrypting token with system's private key");

        jweObject.encrypt(directEncrypter);

        log.info("Token encrypted");

        return jweObject.serialize();
    }
	
	
	
	
	
	
	
}
