package io.augies.menumeapp.model

import groovy.transform.ToString
import org.hibernate.annotations.Formula
import org.springframework.boot.configurationprocessor.json.JSONObject

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = 'restaurant')
@ToString(includeNames = true)
class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name
    String description
    String location
    String logoUrl
    String websiteUrl
    @Enumerated(EnumType.STRING)
    CostLevel costLevel
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory
    Double distance
    String phoneNumber
    @ElementCollection
    @Formula("(SELECT name FROM menume.dietaryrestriction WHERE id IN (SELECT dietaryRestrictionId FROM menume.itemrestriction WHERE itemId IN (SELECT id FROM menume.item WHERE menuId IN (SELECT id FROM menume.menu WHERE menu.id = id))))")
    List<String> dietaryRestrictions

    enum CostLevel {
        LOW,MEDIUM,HIGH
    }

    enum FoodCategory {
        CHINESE,BBQ,PIZZA,ITALIAN,MISCELLANEOUS,AMERICAN,MEXICAN
    }
}
