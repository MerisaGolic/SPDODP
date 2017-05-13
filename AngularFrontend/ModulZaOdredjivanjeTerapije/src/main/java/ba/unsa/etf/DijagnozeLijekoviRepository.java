package ba.unsa.etf;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "dijagnoze_lijekovi", path = "dijagnoze_lijekovi")
public interface DijagnozeLijekoviRepository extends PagingAndSortingRepository<DijagnozeLijekovi, Integer> {

}
