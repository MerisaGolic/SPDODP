package md;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("modul-za-korisnike")
public interface KorisniciClient {
	
	@RequestMapping("/provjeriZadnjuPromjenuPassworda")
	public int provjeriZadnjuPromjenuPassworda(@RequestParam(value="user") String user, @RequestParam(value="issuedAt") String issuedAt, @RequestHeader(value="Cookie") String cookie);
}
