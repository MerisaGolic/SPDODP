package ba.unsa.etf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
