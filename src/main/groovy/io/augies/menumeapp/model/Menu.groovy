package io.augies.menumeapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'menu')
@ToString(includeNames = true)
class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Enumerated(EnumType.STRING)
    MealTime mealTime
    @ManyToOne
    @JoinColumn(name = "restaurantId", nullable = false)
    @JsonIgnore
    Restaurant restaurant
    @OneToMany(mappedBy='menu')
    Set<Item> items

    enum MealTime {
        BREAKFAST, LUNCH, DINNER, MISCELLANEOUS
    }
}
