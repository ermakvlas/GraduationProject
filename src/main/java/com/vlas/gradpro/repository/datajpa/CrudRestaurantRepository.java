package com.vlas.gradpro.repository.datajpa;


import com.vlas.gradpro.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nassuka on 14.12.2016.
 */
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    Restaurant findOne(Integer id);

    @Override
    @Query("SELECT r FROM Restaurant r ORDER BY r.name")
    List<Restaurant> findAll();

    @Query("SELECT r FROM Restaurant r WHERE r.name=?1")
    Restaurant getByName(String name);

//    @EntityGraph(value = User.GRAPH_WITH_MEALS)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithMeals(int id);

}
