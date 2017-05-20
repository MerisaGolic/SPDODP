package ba.unsa.etf.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request,
             ServletResponse response,
             FilterChain filterChain)
      throws IOException, ServletException {
	  HttpServletRequest r = (HttpServletRequest)request;
	  System.out.println(r.getRequestURI());
	  if(r.getRequestURI().equals("/korisnik/sacuvajPassword") == false)
	  {
		  System.out.println("udje");
		  Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest)request); 

		  SecurityContextHolder.getContext()
		  .setAuthentication(authentication);
	  }
		  filterChain.doFilter(request,response);
  }
}
