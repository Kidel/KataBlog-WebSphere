package it.bonofiglio.kata.model.controller;

import com.ibm.jpa.web.JPAManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;
import it.bonofiglio.kata.model.Tag;
import java.util.List;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
@JPAManager(targetEntity = it.bonofiglio.kata.model.Tag.class)
public class TagManager {

	private EntityManagerFactory emf;

	public TagManager() {
	
	}

	public TagManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	@Action(Action.ACTION_TYPE.CREATE)
	public String createTag(Tag tag) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(tag);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.DELETE)
	public String deleteTag(Tag tag) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			tag = em.merge(tag);
			em.remove(tag);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.UPDATE)
	public String updateTag(Tag tag) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			tag = em.merge(tag);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.FIND)
	public Tag findTagById(Long id) {
		Tag tag = null;
		EntityManager em = getEntityManager();
		try {
			tag = (Tag) em.find(Tag.class, id);
		} finally {
			em.close();
		}
		return tag;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public Tag getNewTag() {
	
		Tag tag = new Tag();
	
		return tag;
	}

	@NamedQueryTarget("findAllTags")
	public List<Tag> findAllTags() {
		EntityManager em = getEntityManager();
		List<Tag> results = null;
		try {
			Query query = em.createNamedQuery("findAllTags");
			results = (List<Tag>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

}