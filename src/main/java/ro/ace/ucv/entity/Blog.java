package ro.ace.ucv.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, message = "Invalid URL!")
	@URL(message = "Invalid URL!")
	private String url;
	
	@Size(min = 1, message = "Name must be at least 1 characters!")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(fetch= FetchType.EAGER, mappedBy = "blog", cascade = CascadeType.REMOVE)
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
