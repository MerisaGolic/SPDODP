package ba.unsa.etf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "lijekovi", path = "lijekovi")
public interface LijekoviRepository extends PagingAndSortingRepository<Lijekovi, Integer> {
	
	Lijekovi findById(@Param("id") int id);

	
	@Query("SELECT id FROM Lijekovi WHERE LOWER(naziv) = LOWER(:naziv) ")
	public int vratiIdPremaNazivu(@Param("naziv") String naziv);
	
	@Query( "SELECT l.id "
		+	"FROM Lijekovi l, Dijagnoze d, DijagnozeLijekovi dl "
		+	"WHERE l.id = dl.lijekovi AND d.id = dl.dijagnoze AND d.id = :idDijagnoze")
	public int vratiIdLijeka(@Param("idDijagnoze") int idDijagnoze);
	
}
