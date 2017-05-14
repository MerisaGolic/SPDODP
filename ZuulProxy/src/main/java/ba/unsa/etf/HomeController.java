package ba.unsa.etf;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String index()
	{
		return "index.html";
	}
	
	@RequestMapping(value="/log-in")
	public String login()
	{
		return "login.html";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("Authorization", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	    return "ok";
	}
}
