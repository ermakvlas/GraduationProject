package com.vlas.gradpro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//
//@NamedQueries({
//        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
//        @NamedQuery(name = Restaurant.BY_NAME, query = "SELECT r FROM Restaurant r WHERE r.name=?1"),
//        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
//})

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends NamedEntity {
//
//    public static final String DELETE = "User.delete";
//    public static final String ALL_SORTED = "User.getAllSorted";
//    public static final String BY_NAME = "User.getByName";

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty
    @SafeHtml
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("description DESC")
//    @JsonIgnore
    protected List<Meal> meals;

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
                ", meals=" + meals +
                '}';
    }
}
