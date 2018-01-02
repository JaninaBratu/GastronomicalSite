package ro.ace.ucv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.ace.ucv.entity.Category;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
