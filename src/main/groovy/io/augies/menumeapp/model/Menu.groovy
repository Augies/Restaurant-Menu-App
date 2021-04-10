package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name ='menu')
@ToString(includeNames = true)
class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @Enumerated(EnumType.STRING)
    MealTime mealTime
    @ManyToOne
    @JoinColumn(name="restaurantId", nullable=false)
    Restaurant restaurant

    enum MealTime {
        BREAKFAST,LUNCH,DINNER,MISCELLANEOUS
    }
}
