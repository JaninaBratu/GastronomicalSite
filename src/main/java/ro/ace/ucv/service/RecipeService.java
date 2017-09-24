package ro.ace.ucv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ro.ace.ucv.entity.Blog;
import ro.ace.ucv.entity.Message;
import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.entity.User;
import ro.ace.ucv.repository.MessageRepository;
import ro.ace.ucv.repository.RecipeRepository;
import ro.ace.ucv.repository.UserRepository;

import java.util.List;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;


	public void save(Recipe recipe, String name) {
		User user = userRepository.findByName(name);
		recipe.setUser(user);
		recipeRepository.save(recipe);
	}

	@PreAuthorize("#recipe.user.name == authentication.name")
	public void delete(@P("recipe") Recipe recipe) {
		recipeRepository.delete(recipe);
	}

	public Recipe findOne(int id) {
		return recipeRepository.findOne(id);
	}

	public Recipe findOneWithMessages(int recipeId){

		Recipe recipe = recipeRepository.findOne(recipeId);
		List<Message> messages =  messageRepository.findByRecipe(recipe);

		recipe.setMessages(messages);
		return recipe;
	}
}
