package br.com.caelum.notasfiscais.Dao;

import java.util.List;

import javax.persistence.EntityManager;



import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.validator.constraints.Email;

import br.com.caelum.notasfiscais.modelo.Produto;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
		
	}

	public void adiciona(T t) {
		//consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		//abre a transa��o
		em.getTransaction().begin();
		//persiste o objeto
		em.persist(t);
		//comita trasa��o
		em.getTransaction().commit();
		//fecha o entity manager
		em.close();
		
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		
		List<T> lista = em.createQuery(query).getResultList();
		
		em.close();
		return  lista;
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		
		em.getTransaction().commit();
		em.close();
		
	}
	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		em.merge(t);
		
		em.getTransaction().commit();
		em.close();
	}
	public T buscaPorId(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}
	public T select(T t){
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, t);
		em.close();
		return instancia;
		
	}
	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}
	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();
		em.close();
		
		return (int) result;
	}

	

}
