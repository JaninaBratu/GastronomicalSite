package ro.ace.ucv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 1, message = "Title must be at least 1 characters!")
	private String title;
	
	@Size(min = 1, message = "Content must be at least 10 characters!")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany(mappedBy = "recipe")
	private List<Message> messages;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
