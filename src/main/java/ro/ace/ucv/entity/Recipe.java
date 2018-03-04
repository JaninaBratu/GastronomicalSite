package ro.ace.ucv.entity;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	private String creationDate;

	@OneToMany(fetch=FetchType.EAGER ,mappedBy = "recipe")
	private List<Rating> ratings;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(fetch=FetchType.EAGER ,mappedBy = "recipe")
	private List<Message> messages;

	@ManyToOne
	@JoinColumn (name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn (name="rating_id")
	private Rating rating;

	@NotNull
	private float resultRating;

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

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) { this.messages = messages; }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public float getResultRating() {
		return resultRating;
	}

	public void setResultRating(float resultRating) {
		this.resultRating = resultRating;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
}
