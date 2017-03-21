package ba.unsa.etf;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "korisnici", path = "korisnici")
public interface KorisniciRepository extends PagingAndSortingRepository<Korisnici, Integer> {

}
