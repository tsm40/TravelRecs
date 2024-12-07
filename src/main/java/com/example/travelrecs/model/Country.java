package com.example.travelrecs.model;

import jakarta.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long countryId;  // Primary key for Country entity

    @Column(name = "country_name")
    private String countryName;  // Name of the country

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<State> states = new ArrayList<>();

    public void addState(State state) {
        states.add(state);
        state.setCountry(this);
    }

    public void removeState(State state) {
        states.remove(state);
        state.setCountry(null);
    }
}
