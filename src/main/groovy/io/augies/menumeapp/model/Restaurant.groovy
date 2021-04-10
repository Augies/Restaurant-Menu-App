package io.augies.menumeapp.model

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'Restaurant')
class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
    String location
    String imageUrl
    String websiteUrl
    @Enumerated(EnumType.STRING)
    CostLevel costLevel
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory

    enum CostLevel {
        LOW,MEDIUM,HIGH
    }

    enum FoodCategory {
        CHINESE,BBQ,PIZZA,ITALIAN,MISCELLANEOUS,AMERICAN,MEXICAN
    }
}
