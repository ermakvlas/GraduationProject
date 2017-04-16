package com.vlas.gradpro.repository;


import com.vlas.gradpro.model.Meal;

import java.util.Collection;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int restId);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Meal get(int id);

    // ORDERED dateTime
    List<Meal> getAll(int restId);

    default Meal getWithRestaurant(int id) {
        throw new UnsupportedOperationException();
    }
}
