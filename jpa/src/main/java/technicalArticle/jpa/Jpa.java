/**
 * 
 */
package technicalArticle.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * jpa / Jpa.java
 * FIAP / RM30222 - Vagner Panarello
 */
public class Jpa {
	
	private EntityManagerFactory emf;

	public Jpa() {
		super();
		
		emf = Persistence.createEntityManagerFactory("jpa_implementation");
		
	}
	
	
	
	
	

}
