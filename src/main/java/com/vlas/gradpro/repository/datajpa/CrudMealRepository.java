package com.vlas.gradpro.repository.datajpa;


import com.vlas.gradpro.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    Meal save(Meal item);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restId ORDER BY m.description DESC")
    List<Meal> getAll(@Param("restId") int restId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1")
    Meal getWithRestaurant(int id);
}
