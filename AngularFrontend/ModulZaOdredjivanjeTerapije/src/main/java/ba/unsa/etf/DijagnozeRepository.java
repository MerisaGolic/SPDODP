package ba.unsa.etf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import feign.Param;

@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepository extends PagingAndSortingRepository<Dijagnoze, Integer> {
		
	@Query("SELECT id FROM Dijagnoze WHERE LOWER(naziv) = LOWER(:naziv) ")
	public int vratiIdPremaNazivu(@Param("naziv") String naziv);

}
