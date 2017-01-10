package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by nassuka on 14.12.2016.
 */
public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Restaurant getByName(String name) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    Restaurant getWithMeals(int id);

}
