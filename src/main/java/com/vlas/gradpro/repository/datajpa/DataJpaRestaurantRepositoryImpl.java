package com.vlas.gradpro.repository.datajpa;


import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by nassuka on 14.12.2016.
 */
@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findOne(id);
    }

    @Override
    public Restaurant getByName(String name) {
        return crudRestaurantRepository.getByName(name);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll();
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return crudRestaurantRepository.getWithMeals(id);
    }
}
