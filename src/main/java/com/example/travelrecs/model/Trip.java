package com.example.travelrecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripID;

    private Date startDate;
    private Date endDate;
    private Float totalBudget;
    private Float curBudget;
}
