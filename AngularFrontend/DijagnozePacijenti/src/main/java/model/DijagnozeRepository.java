package model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepository extends PagingAndSortingRepository<Dijagnoze, Integer> {
	Dijagnoze findById(@Param("id") int id);
	
	@Query( "SELECT d.id, d.naziv, d.opis "
		+	"FROM Dijagnoze d, Pacijenti p, DijagnozePacijenti dp "
		+	"WHERE d.id = dp.dijagnoze AND p.id = dp.pacijenti AND p.id = :idPacijenta")
	public List<Object[]> vratiDijagnoze(@Param("idPacijenta") int idPacijenta);
}
