package ro.ace.ucv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 1, message = "Title must be at least 1 characters!")
	private String title;
	
	@Size(min = 1, message = "Content must be at least 10 characters!")
	private String content;
}
