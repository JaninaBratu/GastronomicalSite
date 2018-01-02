package ro.ace.ucv.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(fetch= FetchType.EAGER, mappedBy = "roles")
	private List<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
