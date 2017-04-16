package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.repository.MealRepository;
import com.vlas.gradpro.util.exception.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service("mealService")
public class MealServiceImpl implements MealService {


    @Autowired
    private MealRepository repository;

    @Override
    public Meal get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Meal> getAll(int restId) {
        return repository.getAll(restId);
    }

    @Override
    public Meal update(Meal meal, int restId) {
        Assert.notNull(meal, "meal must not be null");
        return ExceptionUtil.checkNotFoundWithId(repository.save(meal, restId), meal.getId());
    }

    @Override
    public Meal save(Meal meal, int restId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restId);
    }

}
