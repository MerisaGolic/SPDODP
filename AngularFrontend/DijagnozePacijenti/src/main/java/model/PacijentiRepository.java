package model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(collectionResourceRel = "pacijenti", path = "pacijenti")
public interface PacijentiRepository extends PagingAndSortingRepository<Pacijenti, Integer> {
	Pacijenti findById(@Param("id") int id);
	
	@Query("SELECT id FROM Pacijenti p WHERE LOWER(p.imePrezime) = LOWER(:ime_Prezime)")
    public int findIdByName(@Param("ime_Prezime") String ime_Prezime);
	
}
