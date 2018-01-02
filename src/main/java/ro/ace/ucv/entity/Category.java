package ro.ace.ucv.entity;


import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;

    private String categoryType;

    @OneToMany(fetch=FetchType.EAGER ,mappedBy = "category")
    private List<Recipe> recipeList;

    public Category(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}


