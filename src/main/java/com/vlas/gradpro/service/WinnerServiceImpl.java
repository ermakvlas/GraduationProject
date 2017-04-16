package com.vlas.gradpro.service;

import com.vlas.gradpro.model.Restaurant;

import com.vlas.gradpro.repository.RestaurantRepository;
import com.vlas.gradpro.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service("winnerService")
public class WinnerServiceImpl implements WinnerService {

    private Restaurant winnerRest;

    private Map<Restaurant, Long> resultMap;

    @Autowired
    private UserRepository repository;

    private static final String FILEPATH = "C:\\VotingResults.txt";

    //this method will be executed every day, in 11 am
    @Override
    @Scheduled(cron = "0 0 11 * * ?")
    public void getWinner(){

        //creating list of restaurants for which users voted
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        repository.getAll().stream().forEach(user -> restaurants.add(user.getRestaurant()));

        //creating map "restaurant - number of votes"
        resultMap = restaurants.stream().filter(restaurant -> restaurant!=null).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        //determine the winner
        if (!resultMap.isEmpty()){
            winnerRest = resultMap.entrySet().stream().max((r1,r2)->r1.getValue()>r2.getValue() ? 1: -1).get().getKey();
        }

        //writing results of the day in file
        File file = new File(FILEPATH);

        try {
            if (!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fw = new FileWriter(FILEPATH, true);
            BufferedWriter bw = new BufferedWriter(fw)) {

            //writing current date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.now();

            bw.newLine();
            bw.newLine();
            bw.write(dtf.format(date));

            //writing results of voting
            bw.newLine();
            for (Map.Entry<Restaurant,Long> x:resultMap.entrySet()
                 ) {
                String entry = x.getKey().getName() + " - " + x.getValue().toString();
                bw.write(entry);
            }

            bw.newLine();
            bw.write("Winner is: ");
            bw.write(winnerRest.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

        //clearing user votes
        repository.clearAllVotes();

    }

    public Map<Restaurant, Long> getCurrentResults(){
        //creating list of restaurants for which users voted
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        repository.getAll().stream().forEach(user -> restaurants.add(user.getRestaurant()));

        //creating map "restaurant - number of votes"
        Map<Restaurant, Long> currentResultMap = restaurants.stream().filter(restaurant -> restaurant!=null).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        return currentResultMap;
    }

    public Restaurant getCurrentWinner(){

        Restaurant currentWinner=null;

        //creating list of restaurants for which users voted
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        repository.getAll().stream().forEach(user -> restaurants.add(user.getRestaurant()));

        //creating map "restaurant - number of votes"
        Map<Restaurant, Long> resultMap = restaurants.stream().filter(restaurant -> restaurant!=null).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        //determine the winner
        if (!resultMap.isEmpty()){
            currentWinner = resultMap.entrySet().stream().max((r1,r2)->r1.getValue()>r2.getValue() ? 1: -1).get().getKey();
        }

        return currentWinner;
    }
}
