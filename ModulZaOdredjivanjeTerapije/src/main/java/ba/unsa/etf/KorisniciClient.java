package ba.unsa.etf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("modul-za-korisnike")
public interface KorisniciClient {
	@RequestMapping("/provjeriPacijentaTest")
	public Boolean provjeriPacijentaTest();
	
	@RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
	@ResponseBody
	public String brisanjePacijenta(@RequestParam("imePrezime") String imePrezime);
	
	@RequestMapping("/provjeriZadnjuPromjenuPassworda")
	public int provjeriZadnjuPromjenuPassworda(@RequestParam(value="user") String user, @RequestParam(value="issuedAt") String issuedAt, @RequestHeader(value="Cookie") String cookie);
}
