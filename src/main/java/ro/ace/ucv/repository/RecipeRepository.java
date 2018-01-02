package ro.ace.ucv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ace.ucv.entity.Category;
import ro.ace.ucv.entity.Message;
import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.entity.User;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

		List<Recipe> findByUser(User user);
		List<Recipe> findAllBy(Category category);
}
