package com.vlas.gradpro.repository.datajpa;


import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int restId) {
        if (!meal.isNew() && get(meal.getId()) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restId));
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id) {
        Meal meal = crudRepository.findOne(id);
        return meal;
    }

    @Override
    public List<Meal> getAll(int restId) {
        return crudRepository.getAll(restId);
    }

    @Override
    public Meal getWithRestaurant(int id) {
        return crudRepository.getWithRestaurant(id);
    }
}
