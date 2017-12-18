package it.bonofiglio.kata.bean;
import it.bonofiglio.kata.model.Editor;

import javax.ejb.Local;

@Local
public interface LoginBeanLocal {
	public boolean login(String email, String password);
	
	public Editor getCurrentEditor();
	public void setCurrentEditor(Editor currentEditor);
}
