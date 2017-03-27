package model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(collectionResourceRel = "dijagnozepacijenti", path = "dijagnozepacijenti")
public interface DijagnozePacijentiRepository extends PagingAndSortingRepository<DijagnozePacijenti, Integer> {
	

	
}
