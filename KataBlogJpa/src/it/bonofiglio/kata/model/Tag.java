package it.bonofiglio.kata.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllTags", query = "SELECT t FROM Tag t")
public class Tag implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	
	@ManyToMany(mappedBy="tags")
	private List<Post> posts;

	private static final long serialVersionUID = 1L;

	public Tag() {
		super();
		this.posts = new LinkedList<Post>();
	}

	public Tag(String name, List<Post> posts) {
		super();
		this.name = name;
		this.posts = posts;
	}

	public Tag(String name) {
		super();
		this.name = name;
		this.posts = new LinkedList<Post>();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public String toString() {
	   return "{\"name\": \"" + this.name + "\"}";
	}

}
