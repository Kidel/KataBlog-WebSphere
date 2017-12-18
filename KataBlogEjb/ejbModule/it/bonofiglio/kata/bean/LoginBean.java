package it.bonofiglio.kata.bean;

import it.bonofiglio.kata.model.Editor;
import it.bonofiglio.kata.model.controller.EditorManager;

import javax.ejb.Stateful;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * Session Bean implementation class LoginBean
 */
@Stateful
public class LoginBean implements LoginBeanLocal {

    private Editor currentEditor;
    
    @PersistenceUnit(unitName = "KataBlog")
	private EntityManagerFactory entityManagerFactory;
	
	private EditorManager eman;

	public LoginBean() { 
		this.entityManagerFactory = Persistence.createEntityManagerFactory("KataBlog");
    	this.eman = new EditorManager(this.entityManagerFactory);
	}
    
    public boolean login(String email, String password) {
    	Editor currentEditor = eman.findEditorByEmail(email);
    	return currentEditor.getPassword().equals(password);
    }
    
    public Editor getCurrentEditor() {
		return currentEditor;
	}

	public void setCurrentEditor(Editor currentEditor) {
		this.currentEditor = currentEditor;
	}

}
