package it.bonofiglio.kata.bean;

import it.bonofiglio.kata.bean.EditorBeanLocal;
import it.bonofiglio.kata.model.*;
import it.bonofiglio.kata.model.controller.*;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@Stateless
public class EditorBean implements EditorBeanLocal {
	
	@PersistenceUnit(unitName = "KataBlog")
	private EntityManagerFactory entityManagerFactory;
	
	private EditorManager eman;
	
    public EditorBean() { 
    	this.entityManagerFactory = Persistence.createEntityManagerFactory("KataBlog");
    	this.eman = new EditorManager(this.entityManagerFactory);
    }
    
    public Editor createEditor(String name, String email, String password){
    	Editor e = new Editor(name, email, password);
    	try {
			eman.createEditor(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	return e;
    }
	public Editor createEditor(String name, String email, String password,	List<Post> posts){
		Editor e = new Editor(name, email, password, posts);
		try {
			eman.createEditor(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	return e;
	}
	public Editor getEditor(Long id){
		Editor e = eman.findEditorById(id);
		return e;
	}
	
	public Editor getEditorFromEmail(String email){
		return (Editor) eman.findEditorByEmail(email);
	}

	public List<Editor> getAllEditors(){
		return new LinkedList<Editor>(eman.findAllEditors());
	}

}
