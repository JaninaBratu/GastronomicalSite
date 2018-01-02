package ro.ace.ucv.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import ro.ace.ucv.entity.Category;
import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.entity.Message;
import ro.ace.ucv.repository.CategoryRepository;
import ro.ace.ucv.service.CategoryService;
import ro.ace.ucv.service.MessageService;
import ro.ace.ucv.service.RecipeService;
import ro.ace.ucv.service.UserService;
import ro.ace.ucv.utils.*;

@Controller
public class RecipeController {

	@Autowired
	private UserService userService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MessageService messageService;
	private Integer messageId;

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("recipe")
	public Recipe constructRecipe() {
		return new Recipe();
	}

	@ModelAttribute("category")
	public Category constructCategory() {
		return new Category();
	}

	@ModelAttribute("message")
	public Message constructMessage() {
		return new Message();
	}


	@RequestMapping("/user-recipes")
	public String doGetUserRecipes(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithRecipes(name));

		List<Category> categories = categoryService.getListOfCategories();
		model.addAttribute("categories", categories);
		return "user-recipes";
	}

	@RequestMapping(value = "/user-recipes", method = RequestMethod.POST)
	public String doAddRecipe(Principal principal, @RequestBody String requestContent, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return doGetUserRecipes(model, principal);
		}
		String name = principal.getName();
		try {
			requestContent = URLDecoder.decode(requestContent, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String title = JsonUtil.getValueByFilter(JsonUtil.getJsonValues(requestContent), "title");
		String content = JsonUtil.getValueByFilter(JsonUtil.getJsonValues(requestContent), "content");
		String categoryId = JsonUtil.getValueByFilter(JsonUtil.getJsonValues(requestContent), "category");
		Recipe recipe = new Recipe();
		Category myCategory = categoryService.findOne(Integer.valueOf(categoryId));
		recipe.setTitle(title);
		recipe.setContent(content);
		recipe.setCategory(myCategory);
		recipeService.save(recipe, name);
		List<Recipe> myRecipeList = recipeService.findAll();
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
		return "redirect:/recipe-details/" + recipeId + ".html";
	}

	@RequestMapping("/edit-form/{messageId}")
	public String goToEditForm(@PathVariable int messageId, Model model){
		model.addAttribute("message", messageService.findOne(messageId));
		return "edit-comment";
	}

	@RequestMapping(value = "/edit-comment/{messageId}" , method = RequestMethod.GET)
	public String editComment(@PathVariable int messageId, @Valid @ModelAttribute("message") Message newMessage,
							  Principal principal) {

		Message message = messageService.getOne(messageId);
		Integer recipeId = message.getRecipe().getId();
		String userName = principal.getName();
		messageService.edit(messageId, newMessage.getMessage(), userName);
		return "redirect:/recipe-details/" + recipeId + ".html";
}

}
