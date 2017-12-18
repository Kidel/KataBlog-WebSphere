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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllPosts", query = "SELECT p FROM Post p")
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String content;
	@Column(nullable = false)
	private String slug;
	@Column(nullable = false)
	private String title;

	@ManyToMany
	private List<Tag> tags;
	
	@ManyToOne
	private Editor author;

	private static final long serialVersionUID = 1L;

	public Post() {
		super();
		this.tags = new LinkedList<Tag>();
	}

	public Post(String content, String slug, String title, Editor author, List<Tag> tags) {
		super();
		this.content = content;
		this.slug = slug;
		this.title = title;
		this.tags = tags;
		this.author = author;
	}
	
	public Post(String content, String slug, String title, Editor author) {
		super();
		this.content = content;
		this.slug = slug;
		this.title = title;
		this.tags = new LinkedList<Tag>();
		this.author = author;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Editor getAuthor() {
		return this.author;
	}

	public void setAuthor(Editor author) {
		this.author = author;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
	   return "{\"title\": \"" + this.title + "\", \"content\": \"" + this.content + "\"}";
	}

}
