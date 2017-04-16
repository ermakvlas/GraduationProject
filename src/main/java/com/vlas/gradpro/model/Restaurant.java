package com.vlas.gradpro.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends NamedEntity {

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("description DESC")
//    @JsonIgnore
    private List<Meal> meals;

    public Restaurant() {

    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Integer id, String name, String name1, List<Meal> meals) {
        super(id, name);
        this.name = name1;
        this.meals = meals;
    }

    public Restaurant(String name, List<Meal> meals) {
        this.name = name;
        this.meals = meals;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}
