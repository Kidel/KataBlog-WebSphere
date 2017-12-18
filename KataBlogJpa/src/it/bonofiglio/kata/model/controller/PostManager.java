package it.bonofiglio.kata.model.controller;

import com.ibm.jpa.web.JPAManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;

import it.bonofiglio.kata.model.Editor;
import it.bonofiglio.kata.model.Post;

import java.util.List;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
@JPAManager(targetEntity = it.bonofiglio.kata.model.Post.class)
public class PostManager {

	private EntityManagerFactory emf;

	public PostManager() {
	
	}

	public PostManager(EntityManagerFactory emf) {
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
	public String createPost(Post post) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(post);
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
	public String deletePost(Post post) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			post = em.merge(post);
			em.remove(post);
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
	public String updatePost(Post post) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			post = em.merge(post);
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
	public Post findPostById(Long id) {
		Post post = null;
		EntityManager em = getEntityManager();
		try {
			post = (Post) em.find(Post.class, id);
		} finally {
			em.close();
		}
		return post;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public Post getNewPost() {
	
		Post post = new Post();
	
		return post;
	}

	@NamedQueryTarget("findAllPosts")
	public List<Post> findAllPosts() {
		EntityManager em = getEntityManager();
		List<Post> results = null;
		try {
			Query query = em.createNamedQuery("findAllPosts");
			results = (List<Post>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}
	
	@Action(Action.ACTION_TYPE.FIND)
	public List<Post> findAllPostsByAuthor(Editor author) {
		List<Post> posts = null;
		EntityManager em = getEntityManager();
		try {
			posts = (List<Post>) em.createQuery("SELECT p "
		            + "FROM Post p "
					+ "WHERE p.author.id = :idUser")
					.setParameter("idUser", author.getId())
					.getResultList();
		} finally {
			em.close();
		}
		return posts;
	}

}