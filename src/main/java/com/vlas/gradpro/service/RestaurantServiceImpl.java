package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.model.User;
import com.vlas.gradpro.repository.RestaurantRepository;
import com.vlas.gradpro.repository.UserRepository;
import com.vlas.gradpro.util.exception.ExceptionUtil;
import com.vlas.gradpro.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;


/**
 * Created by nassuka on 14.12.2016.
 */
@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Restaurant getByName(String name) throws NotFoundException {
        Assert.notNull(name, "restaurant name can't be null");
        return ExceptionUtil.checkNotFound(repository.getByName(name), "name=" + name);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant can't be null");
        repository.save(restaurant);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.getWithMeals(id), id);
    }

}
