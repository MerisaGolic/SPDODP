package md;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import md.models.Dijagnoze;


@RepositoryRestResource(collectionResourceRel = "dijagnoze", path = "dijagnoze")
public interface DijagnozeRepository extends PagingAndSortingRepository<Dijagnoze, Integer>{
	
	//, count(sd.simptomi)
		 @Query("SELECT d.id, d.naziv, d.opis, d.postotak " 
		      + "FROM Dijagnoze d, Simptomi s, DijagnozeSimptomi sd "
		      + "WHERE s.id = sd.simptomi AND d.id = sd.dijagnoze AND s.naziv IN (:listaSimptoma) "
	 	      + "GROUP BY d.id, d.naziv, d.opis, d.postotak "
	 	      + "HAVING count(*) >= 1 ")
		 public List<Object[]> nadjiDijagnozePoSimptomima(@Param("listaSimptoma") ArrayList<String> listaSimptoma);
		    
		 @Query("SELECT id FROM Dijagnoze WHERE naziv = :naziv ")
		 public int vratiIdPremaNazivu(@Param("naziv") String naziv);
		 
		 @Query("SELECT COUNT(sd.simptomi) "
		      + "FROM Dijagnoze d, Simptomi s, DijagnozeSimptomi sd "
		      + "WHERE s.id = sd.simptomi AND d.id = sd.dijagnoze AND d.id = :id ")
		 public int nadjiBrojSimptoma(@Param("id") Integer id);

		 @Query("SELECT COUNT(sd.simptomi) "
			  + "FROM Dijagnoze d, Simptomi s, DijagnozeSimptomi sd "
		      + "WHERE s.id = sd.simptomi AND d.id = sd.dijagnoze AND d.id = :id AND s.naziv IN (:listaSimptoma) ")
		 public int nadjiBrojPoklapajucihSimptoma(@Param("id") Integer id, @Param("listaSimptoma") ArrayList<String> listaSimptoma);
}
