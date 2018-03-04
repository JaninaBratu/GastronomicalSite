package ro.ace.ucv.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	private RatingRepository ratingRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {

		LocalDate localDate = LocalDate.now();
		String creationDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);

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

		User normalUser = new User();
		normalUser.setEnabled(true);
		normalUser.setName("normalUser");
		BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
		normalUser.setPassword(encoder2.encode("normalUser"));

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);

		List<Role> roles1 = new ArrayList<Role>();
		roles1.add(roleUser);
		normalUser.setRoles(roles1);

		userRepository.save(userAdmin);
		userRepository.save(normalUser);

		Rating rating = new Rating();
		rating.setRatingValue(5);
		ratingRepository.save(rating);

		rating = new Rating();
		rating.setRatingValue(4);
		ratingRepository.save(rating);

		rating = new Rating();
		rating.setRatingValue(3);
		ratingRepository.save(rating);

		rating = new Rating();
		rating.setRatingValue(2);
		ratingRepository.save(rating);

		Recipe recipe = new Recipe();
		recipe.setTitle("Tiramisu");
		recipe.setContent("piscoturi, mascarpone, oua, cafea, cacao");
		recipe.setCategory(categoryRepository.findOne(1));
		recipe.setUser(userAdmin);
		rating.setRecipe(recipe);
		recipe.setCreationDate(creationDate);
		recipeRepository.save(recipe);

		recipe = new Recipe();
		rating = new Rating();
		rating.setRatingValue(Float.parseFloat("4.2"));
		recipe.setTitle("Supa de vacuta");
		recipe.setContent("carne vita, legume, ou, verdeata");
		recipe.setCategory(categoryRepository.findOne(2));
		recipe.setUser(userAdmin);
		recipe.setCreationDate(creationDate);
		recipeRepository.save(recipe);

		recipe = new Recipe();
		recipe.setTitle("Supa de galuste");
		recipe.setContent("gris, ou, verdeata, ulei, pui");
		recipe.setCategory(categoryRepository.findOne(2));
		recipe.setUser(normalUser);
		recipe.setCreationDate(creationDate);
		recipeRepository.save(recipe);

		recipe = new Recipe();
		recipe.setTitle("Supa crema de ciuperci");
		recipe.setContent("ciuperci, unt, smantana, verdeata");
		recipe.setCategory(categoryRepository.findOne(2));
		recipe.setUser(normalUser);
		recipe.setCreationDate(creationDate);
		recipeRepository.save(recipe);

		List<User> usersList = new ArrayList<>();
		usersList.size();

	}
}
