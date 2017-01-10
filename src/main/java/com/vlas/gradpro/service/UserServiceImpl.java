package com.vlas.gradpro.service;


import com.vlas.gradpro.model.Restaurant;
import com.vlas.gradpro.model.User;
import com.vlas.gradpro.repository.RestaurantRepository;
import com.vlas.gradpro.repository.UserRepository;
import com.vlas.gradpro.util.exception.ExceptionUtil;
import com.vlas.gradpro.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    //    @Override
//    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
//        User u = repository.getByEmail(email.toLowerCase());
//        if (u == null) {
//            throw new UsernameNotFoundException("User " + email + " is not found");
//        }
//        return new AuthorizedUser(u);
//    }

}
