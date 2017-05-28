package ba.unsa.etf;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class HomeController {
	
	@RequestMapping(value = "/") //treba
    public String index() {
        return "index.html";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) //treba
	public String login() {
		
		return "prijava.html";
	}
	
	@RequestMapping(value = "/promijeniPassword", method = RequestMethod.GET) //treba
	public String promjenaPassworda() {
		
		return "promjenaPassworda.html";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET) //treba
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("Authorization", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	    return "index.html";
	}
		
	@RequestMapping(value = "/dijagnoze", method = RequestMethod.GET) //treba
	public String nelogovani() {
		
		return "dijagnoza.html";
	}
	@RequestMapping(value = "/drDijagnoze", method = RequestMethod.GET) //treba
	public String logovani() {
		
		return "drDijagnoza.html";
	}
	@RequestMapping(value = "/dodavanjeNoveDijagnoze", method = RequestMethod.GET) //treba
	public String novaDijagnoza() {
		
		return "novaDijagnoza.html";
	}
	
}
