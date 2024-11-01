package com.example.travelrecs.interfaces;

import com.example.travelrecs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
