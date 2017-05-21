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
	
	@RequestMapping(value = "/izvrsiDijagnozu", method = RequestMethod.POST)
	public String dijagnoza() {
		
		return "dijagnoza";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "prijava.html";
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
	
	@RequestMapping(value = "/provjeraLogina", method = RequestMethod.GET)
	public String provjera(@RequestParam("username") String un, @RequestParam("password") String pw) {
		
		System.out.println(un + " "+ pw);
		return "dijagnoza.html";
	}
	@RequestMapping(value = "/provjera", method = RequestMethod.POST)
	public String provjeraa(@RequestBody Korisnik k ) {
		System.out.println(k.getPassword()+ " submit " + k.getUsername());
		
		return "dijagnoza.html";
	}
	
	@RequestMapping(value = "/dijagnoze", method = RequestMethod.GET)
	public String error() {
		
		
		return "dijagnoza.html";
	}
}
