package completo.projeto.completo.Autenticacao;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
   
    private final String chaveSegura = "uma-chave-de-no-mínimo-64-bytes-de-comprimento-para-HS512---segura";
    private final Key key = Keys.hmacShaKeyFor(chaveSegura.getBytes(StandardCharsets.UTF_8)); // Chave segura para HS512
    private final long EXPIRATION_TIME = 86400000; // 1 dia

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS512) // Corrigido aqui
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Corrigido aqui
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); // Corrigido aqui
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
