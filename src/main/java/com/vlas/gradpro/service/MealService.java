package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.util.exception.NotFoundException;

import java.util.Collection;

public interface MealService {
    Meal get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    Collection<Meal> getAll(int restId);

    Meal update(Meal meal, int restId) throws NotFoundException;

    Meal save(Meal meal, int restId);

}
