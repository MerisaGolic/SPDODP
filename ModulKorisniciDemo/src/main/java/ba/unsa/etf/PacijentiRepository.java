package ba.unsa.etf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import javax.transaction.Transactional;


@RepositoryRestResource(collectionResourceRel = "pacijenti", path = "pacijenti")
public interface PacijentiRepository extends PagingAndSortingRepository<Pacijenti, Integer> {
	
	@Query("SELECT id FROM Pacijenti p WHERE LOWER(p.imePrezime) = LOWER(:ime_Prezime)")
    public int findIdByName(@Param("ime_Prezime") String ime_Prezime);
}
