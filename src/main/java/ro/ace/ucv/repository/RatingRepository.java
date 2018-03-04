package ro.ace.ucv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ace.ucv.entity.Rating;
import ro.ace.ucv.entity.Recipe;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findAll();

    List<Rating> findByRecipe(Recipe recipe);
}
