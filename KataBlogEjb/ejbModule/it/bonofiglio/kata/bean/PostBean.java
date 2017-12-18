package it.bonofiglio.kata.bean;

import java.util.LinkedList;
import java.util.List;

import it.bonofiglio.kata.bean.PostBeanLocal;
import it.bonofiglio.kata.model.*;
import it.bonofiglio.kata.model.controller.*;

import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class PostBean implements PostBeanLocal {
	
	@PersistenceUnit(unitName = "KataBlog")
	private EntityManagerFactory entityManagerFactory;
	
	private PostManager pman;
    private EditorManager eman;
	
    public PostBean() { 
    	this.entityManagerFactory = Persistence.createEntityManagerFactory("KataBlog");
		this.pman = new PostManager(this.entityManagerFactory);
		this.eman = new EditorManager(this.entityManagerFactory);
    }
    
    public Post createPost(String title, String content, String slug, Editor author) {
		Post p = new Post(title, content, slug, author);
		try {
			this.pman.createPost(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
    
    public Post createPost(String title, String content, String slug, Editor author, List<Tag> tags) {
		Post p = new Post(title, content, slug, author, tags);
		try {
			this.pman.createPost(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
    
    public Post createPost(String title, String content, String slug, Long authorId) {
		Post p = new Post(title, content, slug, this.eman.findEditorById(authorId));
		try {
			this.pman.createPost(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
    
    public Post getPost(Long id) {
		Post p = pman.findPostById(id);
		return p;
	}

	public List<Post> getAllPosts() {
    	return new LinkedList<Post>(pman.findAllPosts());
	}

	public List<Post> getAllPostsByAuthor(Editor author) {
    	return new LinkedList<Post>(pman.findAllPostsByAuthor(author));
	}

}
