package ro.ace.ucv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

	@RequestMapping("/user-recipes")
	public String doGetuserRecipes() {
		return "user-recipes";
	}

}
