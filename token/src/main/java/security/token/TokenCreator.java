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
public class TokenCreator {
    private SignedJWT createSignedJWT(Authentication auth){
        log.info("Cria sessao JWT");
        Usuario usuario = (Usuario) auth.getPrincipal();
        JWTClaimsSet jwtClaimsSet = createJwtClaimsSet(auth,usuario);
        KeyPair rsaKeys = generateKeyPair();
        log.info("Construindo JWK de RSA keys");

        JWK jwk = new RSAKey.Builder((RSAPublicKey)rsaKeys.getPublic()).keyID(UUID.randomUUID())
    }
}
