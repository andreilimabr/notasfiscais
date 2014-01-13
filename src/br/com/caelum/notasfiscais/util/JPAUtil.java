package br.com.caelum.notasfiscais.util;

import javax.enterprise.inject.Disposes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("nota");
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}
	public void close(@Disposes EntityManager em) {
		em.close();
		
	}
		
	

}
