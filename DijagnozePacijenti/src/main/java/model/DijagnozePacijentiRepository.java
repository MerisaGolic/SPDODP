package model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dijagnozepacijenti", path = "dijagnozepacijenti")
public interface DijagnozePacijentiRepository extends PagingAndSortingRepository<DijagnozePacijenti, Integer> {
	
}
