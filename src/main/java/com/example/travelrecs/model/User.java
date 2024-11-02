package com.example.travelrecs.model;
import com.example.travelrecs.interfaces.UserInterface;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

}
