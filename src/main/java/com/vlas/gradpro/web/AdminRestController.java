package com.vlas.gradpro.web;


import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.model.User;
import com.vlas.gradpro.service.MealService;
import com.vlas.gradpro.service.RestaurantService;
import com.vlas.gradpro.service.UserService;
import com.vlas.gradpro.service.WinnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(AdminRestController.REST_URL)
public class AdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    static final String REST_URL = "/admin";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private MealService mealService;

    @Autowired
    private WinnerService winnerService;

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        log.info("get all users");
        return userService.getAll();
    }

    @GetMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    @GetMapping(value = "/restaurant/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Meal> getAllMeals(@PathVariable("id") int id) {
        log.info("get all restaurants");
        return mealService.getAll(id);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        log.info("delete user " + id);
        userService.delete(id);
    }

    @DeleteMapping(value = "/restaurants")
    public void deleteRestaurant(@RequestParam("id") int id) {
        log.info("delete restaurant " + id);
        restaurantService.delete(id);
    }

    @DeleteMapping(value = "/restaurant/{id}")
    public void deleteMeal(@PathVariable("id") int id, @RequestParam("meal_id") int meal_id) {
        log.info("delete meal " + meal_id + " from restaurant " + id);
        mealService.delete(meal_id);
    }

    @PutMapping(value = "/restaurants/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        restaurant.setId(id);
        log.info("update restaurant " + restaurant);
        restaurantService.update(restaurant);
    }

    @PutMapping(value = "/restaurant/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMeal(@Valid @RequestBody Meal meal, @PathVariable("id") int rest_id, @RequestParam("meal_id") int meal_id) {
        meal.setId(meal_id);
        log.info("update meal " + meal);
        mealService.update(meal, rest_id);
    }

    @PostMapping(value = "/restaurants",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        restaurant.setId(null);
        log.info("create restaurant " + restaurant);

        return new ResponseEntity<Restaurant>(restaurantService.save(restaurant), HttpStatus.CREATED);
    }

    @PostMapping(value = "/restaurant/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createMeal(@Valid @RequestBody Meal meal, @PathVariable("id") int rest_id) {
        meal.setId(null);
        log.info("create meal " + meal + " for restaurant " + rest_id);

        return new ResponseEntity<Meal>(mealService.save(meal,rest_id), HttpStatus.CREATED);
    }

    @PutMapping(value = "/restaurants/vote")
    public void vote(@RequestParam("id") int id){
        restaurantService.vote(id);
    }

    @GetMapping(value = "/restaurants/results", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Long> getCurrentResults(){
        log.info("getting current results");
        return winnerService.getCurrentResults();
    }

    @GetMapping(value = "/restaurants/winner", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getCurrentWinner(){
        log.info("getting current winner");
        return winnerService.getCurrentWinner();
    }

}
