package br.com.bandtec.bora.token.security.util.security.token;

import br.com.bandtec.bora.core.property.JwtConfiguration;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.AccessDeniedException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenConverter {
	private final JwtConfiguration jwtConfiguration;

	public String decryptToken(String encryptedToken) throws ParseException, JOSEException {
		log.info("Descriptando token");

		JWEObject jweObject = JWEObject.parse(encryptedToken);

		DirectDecrypter directDecrypter = new DirectDecrypter(jwtConfiguration.getPrivateKey().getBytes());

		jweObject.decrypt(directDecrypter);

		log.info("Token descriptado, retornando token...");

		return jweObject.getPayload().toSignedJWT().serialize();
	}

	public void validateTokenSignature(String signedToken) throws ParseException, AccessDeniedException, JOSEException {
		log.info("Inicia metodo para validar token...");

		SignedJWT signedJWT = SignedJWT.parse(signedToken);

		log.info("Token parsed! Retrieving public key from signed token");

		RSAKey publicKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());

		log.info("Public key retrieved, validating signature...");

		if (!signedJWT.verify(new RSASSAVerifier(publicKey)))
			throw new AccessDeniedException("invalid token signature");

		log.info("Token tem uma assinatura valida");
	}

}
