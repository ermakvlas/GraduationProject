package com.vlas.gradpro.web;

import com.vlas.gradpro.model.Meal;
import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.service.MealService;
import com.vlas.gradpro.service.RestaurantService;
import com.vlas.gradpro.service.WinnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);
    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService service;

    @Autowired
    private WinnerService winnerService;

    @GetMapping
    public List<Restaurant> getAll(){
        LOG.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getWithMeals(@PathVariable("id") int id) {
        LOG.info("get " + id);
        return service.getWithMeals(id);
    }

    @PutMapping(value = "/vote")
    public void vote(@RequestParam("id") int id){
        service.vote(id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getByName(@RequestParam("name") String name) {
        LOG.info("getByName " + name);
        return service.getByName(name);
    }

    @GetMapping(value = "/results", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Restaurant, Long> getCurrentResults(){
        LOG.info("getting current results");
        return winnerService.getCurrentResults();
    }


}
