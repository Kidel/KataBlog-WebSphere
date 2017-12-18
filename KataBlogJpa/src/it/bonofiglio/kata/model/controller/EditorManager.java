package it.bonofiglio.kata.model.controller;

import com.ibm.jpa.web.JPAManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;
import it.bonofiglio.kata.model.Editor;
import java.util.List;
import javax.persistence.Query;

@SuppressWarnings("unchecked")
@JPAManager(targetEntity = it.bonofiglio.kata.model.Editor.class)
public class EditorManager {

	private EntityManagerFactory emf;

	public EditorManager() {
	
	}

	public EditorManager(EntityManagerFactory emf) {
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
	public String createEditor(Editor editor) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(editor);
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
	public String deleteEditor(Editor editor) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			editor = em.merge(editor);
			em.remove(editor);
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
	public String updateEditor(Editor editor) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			editor = em.merge(editor);
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
	public Editor findEditorById(Long id) {
		Editor editor = null;
		EntityManager em = getEntityManager();
		try {
			editor = (Editor) em.find(Editor.class, id);
		} finally {
			em.close();
		}
		return editor;
	}
	
	@Action(Action.ACTION_TYPE.FIND)
	public Editor findEditorByEmail(String email) {
		Editor editor = null;
		EntityManager em = getEntityManager();
		try {
			editor = (Editor) em.createQuery("SELECT u FROM Editor u WHERE u.email = :email")
								.setParameter("email", email)
								.getSingleResult();
		} finally {
			em.close();
		}
		return editor;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public Editor getNewEditor() {
	
		Editor editor = new Editor();
	
		return editor;
	}

	@NamedQueryTarget("findAllEditors")
	public List<Editor> findAllEditors() {
		EntityManager em = getEntityManager();
		List<Editor> results = null;
		try {
			Query query = em.createNamedQuery("findAllEditors");
			results = (List<Editor>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

}