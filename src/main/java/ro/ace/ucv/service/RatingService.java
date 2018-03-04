package ro.ace.ucv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ace.ucv.entity.Rating;
import ro.ace.ucv.entity.Recipe;
import ro.ace.ucv.repository.CategoryRepository;
import ro.ace.ucv.repository.RatingRepository;
import ro.ace.ucv.repository.RecipeRepository;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public Rating findOne(int id) {
        return ratingRepository.findOne(id);
    }

    public List<Rating> getListOfRatings() {
         return ratingRepository.findAll();
    }

   }
