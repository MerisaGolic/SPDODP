package ba.unsa.etf;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "korisnici", path = "korisnici")
public interface KorisniciRepository extends PagingAndSortingRepository<Korisnici, Integer> {
	
	@Query("SELECT k FROM Korisnici k WHERE LOWER(k.username) = LOWER(:user) AND LOWER(k.password) = LOWER(:pass)")
    public Korisnici provjeriLogin(@Param("user") String user, @Param("pass") String pass);
	
	Korisnici findByUsername(String username);
	
	Korisnici findByEmail(String email);
}
