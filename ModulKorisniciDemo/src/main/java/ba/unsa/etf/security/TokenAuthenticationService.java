package ba.unsa.etf.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import ba.unsa.etf.Controller;
import ba.unsa.etf.Korisnici;
import ba.unsa.etf.KorisniciRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

import java.sql.Timestamp;
import java.text.ParseException;

@Component
public class TokenAuthenticationService {
	
	static KorisniciRepository kr;
	
	@Autowired
	public TokenAuthenticationService(KorisniciRepository kr)
	{
		TokenAuthenticationService.kr = kr;
	}
	
	
  static final long EXPIRATIONTIME = 864_000_000; // 10 dana
  static final String SECRET = "ThisIsASecret";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";

  static void addAuthentication(HttpServletResponse res, String username) {
    String JWT = Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    Cookie cookie = new Cookie(HEADER_STRING, JWT);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    res.addCookie(cookie);
  }

  static Authentication getAuthentication(HttpServletRequest request){
	Cookie cookies[] = request.getCookies();
	Cookie cookie = new Cookie("naziv", null);
	if(cookies != null)
	{
		for(int i = 0; i < cookies.length; i++)
		{
			if(cookies[i].getName().equals("Authorization"))
			{
				cookie = cookies[i];
				break;
			}
	    }
	}
	String token = cookie.getValue();
	String novi = "Authorization=" + token;
	System.out.println(novi);
    if (token != null) 
    {
      // parse the token.
      Date issuedAt = Jwts.parser()
    		  .setSigningKey(SECRET)
    		  .parseClaimsJws(" " + token)
    		  .getBody()
    		  .getIssuedAt();

      String user = Jwts.parser()
    		  .setSigningKey(SECRET)
    		  .parseClaimsJws(" "+ token)
    		  .getBody()
    		  .getSubject();
      System.out.println(user);
      System.out.println(issuedAt);
      
      
      Korisnici k = kr.findByUsername(user);
      int poredjenje = k.getPromjenaPassworda().compareTo(issuedAt);
      
      System.out.println(poredjenje);

      /*return user != null ?
          new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
          null;*/
      if(user != null && poredjenje < 0)
    	  return new UsernamePasswordAuthenticationToken(user, null, emptyList());
      else
    	  return null;
 
    }
    return null;
  }
}
