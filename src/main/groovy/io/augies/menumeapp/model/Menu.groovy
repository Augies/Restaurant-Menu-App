package io.augies.menumeapp.model

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
@Table(name ='Menu')
class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @Enumerated(EnumType.STRING)
    MealTime mealTime
    @ManyToOne
    @JoinColumn(name="restaurantId", nullable=false)
    Long restaurantId

    enum MealTime {
        BREAKFAST,LUNCH,DINNER,MISCELLANEOUS
    }
}
