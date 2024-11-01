package com.example.travelrecs.interfaces;

import com.example.travelrecs.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayInterface extends JpaRepository<Day, Integer> {
}
