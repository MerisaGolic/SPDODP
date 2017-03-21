package md;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepositoy extends PagingAndSortingRepository<Dijagnoze, Integer>{

}
