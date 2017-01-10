package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.model.User;
import com.vlas.gradpro.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);

}
