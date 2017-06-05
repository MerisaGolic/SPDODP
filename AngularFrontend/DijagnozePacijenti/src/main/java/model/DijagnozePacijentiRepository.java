package model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(collectionResourceRel = "dijagnozepacijenti", path = "dijagnozepacijenti")
public interface DijagnozePacijentiRepository extends PagingAndSortingRepository<DijagnozePacijenti, Integer> {
	
	@Query("SELECT id FROM DijagnozePacijenti dp WHERE LOWER(dp.pacijenti) = LOWER(:id_pacijenta)")
	public List<Integer> findIdByIdPacijenta (@Param("id_pacijenta") int id_pacijenta);

	
}
