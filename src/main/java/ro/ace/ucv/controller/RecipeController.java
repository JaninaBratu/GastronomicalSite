package ro.ace.ucv.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.service.RecipeService;
import ro.ace.ucv.service.UserService;

@Controller
public class RecipeController {

	@Autowired
	private UserService userService;


	@Autowired
	private RecipeService recipeService;

	@ModelAttribute("recipe")
	public Recipe constructRecipe() {
		return new Recipe();
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
		return "redirect:/user-recipes.html";
	}

	@RequestMapping("/recipe/remove/{id}")
	public String removeRecipe(@PathVariable int id) {
		Recipe recipe = recipeService.findOne(id);
		recipeService.delete(recipe);
		return "redirect:/user-recipes.html";
	}
	
}
