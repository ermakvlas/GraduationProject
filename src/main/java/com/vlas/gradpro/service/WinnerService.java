package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Restaurant;

import java.util.Map;

public interface WinnerService {

    void getWinner();

    Map<Restaurant, Long> getCurrentResults();

    Restaurant getCurrentWinner();

}
