package md;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import md.models.Simptomi;

@RepositoryRestResource(collectionResourceRel = "simptomi", path = "simptomi")
public interface SimptomiRepository extends PagingAndSortingRepository<Simptomi, Integer> {
	
	 @Query("SELECT id FROM Simptomi WHERE naziv = :naziv ")
	 public int vratiIdPremaNazivu(@Param("naziv") String naziv);

}
