package ba.unsa.etf.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ba.unsa.etf.Controller;
import ba.unsa.etf.Korisnici;
import ba.unsa.etf.KorisniciRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Date;

import static java.util.Collections.emptyList;

import java.sql.Timestamp;
import java.text.ParseException;

public class PasswordResetTokenAuthenticationService {
	
	public PasswordResetTokenAuthenticationService()
	{
		
	}
	
	static final long EXPIRATIONTIME = 86_400_000; // 1 dan
	static final String SECRET = "ThisIsASecret";
	
	public String kreirajToken(String username)
	{
		String JWT = Jwts.builder()
		        .setSubject(username)
		        .setIssuedAt(new Date(System.currentTimeMillis()))
		        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
		        .signWith(SignatureAlgorithm.HS512, SECRET)
		        .compact();
		return JWT;
	}
	
	public String validirajToken(String token)
	{
		if(token != null)
		{
			String user = Jwts.parser()
		    		  .setSigningKey(SECRET)
		    		  .parseClaimsJws(" "+ token)
		    		  .getBody()
		    		  .getSubject();
			
			if(user != null)
			{
				Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
				SecurityContextHolder.getContext().setAuthentication(auth);
				System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		    	return "Password reset token validan!";
			}
		    else
		    	return "Password reset token nije validan!";
		}
		return "Nema password reset tokena!";
	}

}
