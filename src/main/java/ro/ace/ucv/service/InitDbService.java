package ro.ace.ucv.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ro.ace.ucv.entity.*;
import ro.ace.ucv.repository.*;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		Category sweetCategory = new Category();
		sweetCategory.setCategoryType("Sweet");
		categoryRepository.save(sweetCategory);

		Category soupCategory = new Category();
		soupCategory.setCategoryType("Soup");
		categoryRepository.save(soupCategory);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);

		userRepository.save(userAdmin);

		Recipe recipe = new Recipe();
		recipe.setTitle("Tiramisu");
		recipe.setContent("piscoturi, mascarpone, oua, cafea, cacao");
		recipe.setCategory(categoryRepository.findOne(1));
		recipe.setUser(userAdmin);
		recipeRepository.save(recipe);

		recipe = new Recipe();
		recipe.setTitle("Supa de vacuta");
		recipe.setContent("carne vita, legume, ou, verdeata");
		recipe.setCategory(categoryRepository.findOne(2));
		recipe.setUser(userAdmin);
		recipeRepository.save(recipe);
	}
}
