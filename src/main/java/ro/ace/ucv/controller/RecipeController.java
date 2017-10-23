package ro.ace.ucv.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.entity.Message;
import ro.ace.ucv.service.MessageService;
import ro.ace.ucv.service.RecipeService;
import ro.ace.ucv.service.UserService;

@Controller
public class RecipeController {

	@Autowired
	private UserService userService;


	@Autowired
	private RecipeService recipeService;


	@Autowired
	private MessageService messageService;

	private Integer messageId;

	@ModelAttribute("recipe")
	public Recipe constructRecipe() {
		return new Recipe();
	}
	
	
	@ModelAttribute("message")
	public Message constructMessage() {
		return new Message();
	}
	
	
	@RequestMapping("/user-recipes")
	public String doGetuserRecipes(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithRecipes(name));
		return "user-recipes";
	}

	@RequestMapping(value = "/user-recipes", method = RequestMethod.POST)
	public String doAddRecipe(Model model, @Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result, Principal principal) {
		
		if(result.hasErrors()) {
			return doGetuserRecipes(model, principal);
		}
		String name = principal.getName();
		recipeService.save(recipe, name);
		System.out.println("The id of the recipe added is: " + recipe.getId());
		return "redirect:/user-recipes.html";
	}

	@RequestMapping("/recipe/remove/{id}")
	public String removeRecipe(@PathVariable int id) {
		Recipe recipe = recipeService.findOne(id);
		recipeService.delete(recipe);
		return "redirect:/user-recipes.html";
	}

	@RequestMapping("/comment/remove/{id}")
	public String removeComment(@PathVariable int id){
		Message message  = messageService.findOne(id);
		Integer recipeId = message.getRecipe().getId();
		messageService.delete(message);
		return "redirect:/recipe-details/" + recipeId + ".html";
	}

	@RequestMapping("/recipe-details/{id}")
	public String getRecipeDetails(@PathVariable int id, Model model) {

		model.addAttribute("recipe", recipeService.findOneWithMessages(id));
		return "recipe-details";
	}

	@RequestMapping(value = "/recipe-details/{recipeId}", method = RequestMethod.POST)
	public String addRecipeComment(@PathVariable int recipeId, @Valid @ModelAttribute("message") Message message, Principal principal) {

		messageService.save(message, principal, recipeId);
		System.out.println("The id of the comment added is: " + message.getId());
		messageId = message.getId();
		System.out.println("The message added is: " + message.getId() + " " + message.getMessage());
		return "redirect:/recipe-details/" + recipeId + ".html";
	}
}
