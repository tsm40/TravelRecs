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
            //在加载父实体时，是否立即加载关联的子实体。
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<State> states = new ArrayList<>();

    // 辅助方法：添加 State
    public void addState(State state) {
        states.add(state);
        state.setCountry(this);
    }

    // 辅助方法：移除 State
    public void removeState(State state) {
        states.remove(state);
        state.setCountry(null);
    }
}
