package md;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "simptomi", path = "simptomi")
public interface SimptomiRepository extends PagingAndSortingRepository<Simptomi, Integer> {

}
