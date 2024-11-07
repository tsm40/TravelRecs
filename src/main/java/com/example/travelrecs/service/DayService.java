package com.example.travelrecs.service;

import com.example.travelrecs.dto.DayDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DayService {
    DayDTO createDay(DayDTO dayDTO);
    Page<DayDTO> getAllDays(Pageable pageable);
    Optional<DayDTO> getDayById(Long id);
    DayDTO updateDay(Long id, DayDTO dayDTO);
    void deleteDay(Long id);
}