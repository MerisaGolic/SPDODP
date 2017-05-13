package ba.unsa.etf;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pacijenti_korisnici", path = "pacijenti_korisnici")
public interface PacijentiKorisniciRepository extends PagingAndSortingRepository<PacijentiKorisnici, Integer> {

}
