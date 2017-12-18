package it.bonofiglio.kata.bean;
import it.bonofiglio.kata.model.Editor;
import it.bonofiglio.kata.model.Post;

import java.util.List;

import javax.ejb.Local;

@Local
public interface EditorBeanLocal {
	public Editor createEditor(String name, String email, String password);
	public Editor createEditor(String name, String email, String password,	List<Post> posts);
	public Editor getEditor(Long id);
	public Editor getEditorFromEmail(String email);
	public List<Editor> getAllEditors();
}
