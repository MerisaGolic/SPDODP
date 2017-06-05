package ba.unsa.etf;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class HomeController {
	
	@RequestMapping(value = "/") 
    public String index() {
        return "index.html";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "prijava.html";
	}
	
	@RequestMapping(value = "/promijeniPassword", method = RequestMethod.GET) 
	public String promjenaPassworda() {
		
		return "promjenaPassworda.html";
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET) 
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("Authorization", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	    return "index.html";
	}
		
	@RequestMapping(value = "/dijagnoze", method = RequestMethod.GET) 
	public String nelogovani() {
		
		return "dijagnoza.html";
	}
	@RequestMapping(value = "/drDijagnoze", method = RequestMethod.GET) 
	public String logovani() {
		
		return "drDijagnoza.html";
	}
	@RequestMapping(value = "/dodavanjeNoveDijagnoze", method = RequestMethod.GET) 
	public String novaDijagnoza() {
		
		return "novaDijagnoza.html";
	}
	@RequestMapping(value = "/dodavanjeLijeka", method = RequestMethod.GET) 
	public String noviLijek() {
		
		return "noviLijek.html";
	}
	@RequestMapping(value = "/pacijenti", method = RequestMethod.GET) 
	public String pacijenti() {
		
		return "pregledPacijenata.html";
	}
	
	
}
