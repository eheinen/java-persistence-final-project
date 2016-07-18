package technicalArticle.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import technicalArticle.entity.Cliente;
import technicalArticle.entity.Quarto;

public class App 
{
	
	private static EntityManagerFactory emf;
	
    public static void main( String[] args )
    {
        
    	
    	emf = Persistence.createEntityManagerFactory("jpa_implementation");
    	
    	EntityManager em = emf.createEntityManager();
    	
    	
    	Cliente cliente = new Cliente("Vagner", "1000", 0);
    	
    	List<Quarto> quartos = new ArrayList<Quarto>();
    	
    	quartos.add(new Quarto("01", "400"));
    	quartos.add(new Quarto("02", "400"));
    	quartos.add(new Quarto("03", "400"));
    	quartos.add(new Quarto("04", "400"));
    	
    	
    	for (Quarto quarto : quartos) {
			quarto.setCliente(cliente);
		}
    	
    	em.getTransaction().begin();
    	
    	for (Quarto quarto : quartos) {
			em.persist(quarto);
		}
    	
    	em.persist(cliente);
    	
    	em.getTransaction().commit();
    	em.close();
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
