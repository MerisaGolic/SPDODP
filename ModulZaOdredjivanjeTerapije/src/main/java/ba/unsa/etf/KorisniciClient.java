package ba.unsa.etf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("modul-za-korisnike")
public interface KorisniciClient {
	@RequestMapping("/provjeriPacijentaTest")
	public Boolean provjeriPacijentaTest();
}
