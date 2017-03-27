package md;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dijagnozesimptomi", path = "dijagnozesimptomi")
public interface DijagnozeSimptomiRepository extends PagingAndSortingRepository<DijagnozeSimptomi, Integer> {

}
