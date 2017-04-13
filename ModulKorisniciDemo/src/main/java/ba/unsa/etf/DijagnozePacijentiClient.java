package ba.unsa.etf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ba.unsa.etf.Pacijenti;



@FeignClient("modulDijagnozePacijenti")
public interface DijagnozePacijentiClient {
	@RequestMapping(method=RequestMethod.POST, value = "/unosPacijenta")
	 @ResponseBody
	 public Pacijenti unosPacijenta (@RequestBody Pacijenti req);
	
	 @RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
		@ResponseBody
		public String brisanjePacijenta(@RequestParam("imePrezime") String imePrezime);
}
