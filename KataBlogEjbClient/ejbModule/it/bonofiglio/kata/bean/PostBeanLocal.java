package it.bonofiglio.kata.bean;
import it.bonofiglio.kata.model.Editor;
import it.bonofiglio.kata.model.Post;
import it.bonofiglio.kata.model.Tag;

import java.util.List;

import javax.ejb.Local;

@Local
public interface PostBeanLocal {
	public Post createPost(String title, String content, String slug, Editor author, List<Tag> tags);
	public Post createPost(String title, String content, String slug, Editor author);
	public Post getPost(Long id);
	public List<Post> getAllPosts();
	public List<Post> getAllPostsByAuthor(Editor author);
}
