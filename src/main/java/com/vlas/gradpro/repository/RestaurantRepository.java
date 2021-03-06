package com.vlas.gradpro.repository;


import com.vlas.gradpro.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int restId);

    // null if not found
    Restaurant get(int restId);

    // null if not found
    Restaurant getByName(String name);

    List<Restaurant> getAll();

    default Restaurant getWithMeals(int restId){
        throw new UnsupportedOperationException();
    }
}
