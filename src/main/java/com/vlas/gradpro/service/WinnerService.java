package com.vlas.gradpro.service;

import com.vlas.gradpro.model.Restaurant;

import com.vlas.gradpro.repository.RestaurantRepository;
import com.vlas.gradpro.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("winnerService")
public class WinnerService {

    private Restaurant winnerRest;

    @Autowired
    private UserRepository repository;

    @Scheduled(cron = "0 0 11 * * ?")
    public void getWinner(){

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        repository.getAll().stream().forEach(user -> restaurants.add(user.getRestaurant()));

        System.out.println("Список ресторанов, за которые голосовали");
        restaurants.stream().forEach(System.out::println);

        Map<Restaurant, Long> resultMap = restaurants.stream().filter(restaurant -> restaurant!=null).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        System.out.println("Список ресторанов + голоса");
        resultMap.entrySet().stream().forEach(System.out::println);

        if (!resultMap.isEmpty()){
            winnerRest = resultMap.entrySet().stream().max((r1,r2)->r1.getValue()>r2.getValue() ? 1: -1).get().getKey();
            System.out.println(winnerRest);
        }


    }
}
