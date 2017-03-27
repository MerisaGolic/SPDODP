package ba.unsa.etf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import javax.transaction.Transactional;


@RepositoryRestResource(collectionResourceRel = "pacijenti", path = "pacijenti")
public interface PacijentiRepository extends PagingAndSortingRepository<Pacijenti, Integer> {
	
	@Query("SELECT id FROM Pacijenti p WHERE LOWER(p.imePrezime) = LOWER(:ime_Prezime)")
    public int findIdByName(@Param("ime_Prezime") String ime_Prezime);
	
	@Query( "SELECT p.datumRodjenja, p.imePrezime, p.spol "
			+	"FROM Pacijenti p, Korisnici k, PacijentiKorisnici pk "
			+	"WHERE p.id = pk.pacijenti AND k.id = pk.korisnici AND k.id = :idKorisnika")
		public List<Pacijenti> vratiPacijente(@Param("idKorisnika") int idKorisnika);
}
