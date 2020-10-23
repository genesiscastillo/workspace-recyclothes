package cl.ccastillo.recyclothes.jwt;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtService {
	
	public String getTokenJWT(String data , Integer minutosExpiracion) {// data -> puede ser email , nombre , uuid, otro identificador unique
		String base64EncodedSecretKey = Base64.getEncoder().encodeToString(data.getBytes());
		Long tiempo = System.currentTimeMillis();
		Long minuto = Duration.ofMinutes(minutosExpiracion).toMillis();
		String jwt = Jwts.builder()
						.setId(UUID.randomUUID().toString())
						.signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
						.setSubject("Usuario Por Autorizar")
						.setIssuedAt( new Date( tiempo ))
						.setExpiration(new Date( tiempo + minuto ))
						.claim("email", "genesiscastillo@hotmail.com")
						.compact();
		return jwt;
	}
	
	public void validateEmailTokenJWT(String email, String jwt) throws Exception	{
		validateTokenJWT(email, jwt);
	}

	private void validateTokenJWT(String email, String jwt) throws Exception {
		String base64EncodedSecretKey = Base64.getEncoder().encodeToString(email.getBytes());
		Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(jwt).getBody();
	}
}
