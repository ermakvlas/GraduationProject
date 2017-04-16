package com.vlas.gradpro.repository.datajpa;


import com.vlas.gradpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE User SET restaurant = null")
    void clearAllVotes();

    @Override
    @Transactional
    User save(User user);

    @Override
    User findOne(Integer id);

    @Override
    @Query("SELECT u FROM User u ORDER BY u.name, u.email")
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.email=?1")
    User getByEmail(String email);

}
