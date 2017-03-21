package ba.unsa.etf;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pacijenti", path = "pacijenti")
public interface PacijentiRepository extends PagingAndSortingRepository<Pacijenti, Integer> {

}
