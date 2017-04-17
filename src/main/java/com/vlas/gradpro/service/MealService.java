package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Meal> getAll(int restId);

    Meal update(Meal meal, int restId) throws NotFoundException;

    Meal save(Meal meal, int restId);

    Meal getWithRestaurant(int id, int restId);

}
