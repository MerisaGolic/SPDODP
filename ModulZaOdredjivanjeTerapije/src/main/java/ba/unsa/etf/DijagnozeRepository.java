package ba.unsa.etf;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepository extends PagingAndSortingRepository<Dijagnoze, Integer> {

}
