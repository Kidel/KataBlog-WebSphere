package it.bonofiglio.kata.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "findAllEditors", query = "SELECT e FROM Editor e")
public class Editor implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy="author")
	private List<Post> posts;

	private static final long serialVersionUID = 1L;

	public Editor() {
		super();
		this.posts = new LinkedList<Post>();
	}
	
	public Editor(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.posts = new LinkedList<Post>();
	}
	
	public Editor(String email, String name, String password, List<Post> posts) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.posts = posts;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
	   return "{\"name\": \"" + this.name + "\", \"email\": \"" + this.email + "\"}";
	}

}
