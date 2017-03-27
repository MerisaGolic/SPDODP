package md;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepository extends PagingAndSortingRepository<Dijagnoze, Integer>{
	
	 @Query("SELECT d.naziv, d.opis, count(sd.simptomi) "
	      + "FROM Dijagnoze d, Simptomi s, DijagnozeSimptomi sd "
	      + "WHERE s.id = sd.simptomi and d.id = sd.dijagnoze AND s.naziv IN (:listaSimptoma) "
 	      + "GROUP BY d.naziv, d.opis "
 	      + "HAVING count(*) >= 1 ")
	 public List<Dijagnoze> nadjiDijagnozePoSimptomima(@Param("listaSimptoma") ArrayList<String> listaSimptoma);
	    
	 @Query("SELECT id FROM Dijagnoze WHERE naziv = :naziv ")
	 public int vratiIdPremaNazivu(@Param("naziv") String naziv);
}
