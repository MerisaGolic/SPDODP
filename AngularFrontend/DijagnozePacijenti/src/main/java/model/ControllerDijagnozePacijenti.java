
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerDijagnozePacijenti {


	@Autowired
	private PacijentiRepository pRepo;

	@Autowired
	private DijagnozeRepository dRepo;

	@Autowired
	private DijagnozePacijentiRepository dpRepo;

	@RequestMapping(value="/poveziDijagnozuPacijenta", method = RequestMethod.GET)
	public void poveziDijagnozuPacijenta(@RequestParam(value="idPacijenta", defaultValue="0") int idPacijenta, @RequestParam(value="idDijagnoze", defaultValue="0") int idDijagnoze)
	{
		Pacijenti p = pRepo.findOne(idPacijenta);
		Dijagnoze d = dRepo.findOne(idDijagnoze);
		DijagnozePacijenti dp = new DijagnozePacijenti();
		dp.setDatumDijagnoze(new Date(System.currentTimeMillis()));
		dp.setDijagnoze(d);
		dp.setPacijenti(p);
		
		dp.setId(idPacijenta, idDijagnoze);		
		dpRepo.save(dp);

		p.addDijagnozePacijenti(dp);
		d.addDijagnozePacijenti(dp);
	}

	@RequestMapping("/sveDijagnozePacijenta")
	public List<Dijagnoze> vratiDijagnozePacijenta(@RequestParam(value="idPacijenta", defaultValue="0") int idPacijenta)
	{
		//Pacijenti p = pRepo.findOne(idPacijenta);
		List<Object[]> dijagnozePacijenta = dRepo.vratiDijagnoze(idPacijenta);
		List<Dijagnoze> lista = new ArrayList<Dijagnoze>();
		
		for(int i=0; i<dijagnozePacijenta.size(); i++ )
		{
			Dijagnoze d = new Dijagnoze();
			d.setId((Integer)dijagnozePacijenta.get(i)[0]);
			d.setNaziv((String)dijagnozePacijenta.get(i)[1]);
			d.setOpis((String)dijagnozePacijenta.get(i)[2]);
			
			lista.add(d);
		}

		return lista; //dijagnozePacijenta;

	}

	@RequestMapping("/provjeriDijagnozuTest")
	public Boolean provjeriDijagnozuTest()
	{

		Dijagnoze d = dRepo.findOne(1);
		if(d == null)
		{
			return false;
		}
		return true;
	}

	@RequestMapping(value="/provjeriDijagnozuTestParam", method = RequestMethod.GET)
	@ResponseBody
	public Boolean provjeriDijagnozuTestParam(@RequestParam(value="id")Integer id)
	{
		Dijagnoze d = dRepo.findOne(id);
		if(d == null)
		{
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/prijemDijagnoze1", method = RequestMethod.POST)
	@ResponseBody
	public String prijemDijagnoze1(@RequestBody Dijagnoze req, @RequestHeader(value="Cookie") String token)
	{
		//ovdje insert
		dRepo.save(req);

		return "sve proslo - armin";
	}

	@RequestMapping(method=RequestMethod.POST, value = "/unosPacijenta")
	@ResponseBody
	public Pacijenti unosPacijenta (@RequestBody Pacijenti req, @RequestHeader(value="Cookie") String token){

		Pacijenti p = new Pacijenti();
		p=pRepo.save(req);
		return p;
	}

	@RequestMapping(value= "/brisanjePacijentaPoImenuIPrezimenu", method=RequestMethod.GET)
	@ResponseBody
	public String brisanjePacijenta(@RequestParam("imePrezime") String imePrezime, @RequestHeader(value="Cookie") String cookie) {

		int id = pRepo.findIdByName(imePrezime);
		
		List<Integer> lista_id_dijagnoza = dpRepo.findIdByIdPacijenta(id);
		
		for(int i=0; i<lista_id_dijagnoza.size(); i++) {
			dpRepo.delete(lista_id_dijagnoza.get(i));
		}
		
		pRepo.delete(id);
		return "User succesfully deleted!";
		
	}

}
