package com.vlas.gradpro.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nassuka on 09.01.2017.
 */
@RestController
@RequestMapping(UserRestController.REST_URL)
public class UserRestController {
    static final String REST_URL = "/users";


}
