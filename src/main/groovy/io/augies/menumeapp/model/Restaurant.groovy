package io.augies.menumeapp.model

import groovy.transform.ToString
import org.hibernate.annotations.Formula

import javax.persistence.*

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
    @Basic
    Integer costLevelValue //https://www.baeldung.com/jpa-persisting-enums-in-jpa
    @Transient
    CostLevel costLevel
    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory
    Double distance
    String phoneNumber
    @ElementCollection
    @Formula("(SELECT name FROM menume.dietaryrestriction WHERE id IN (SELECT dietaryRestrictionId FROM menume.itemrestriction WHERE itemId IN (SELECT id FROM menume.item WHERE menuId IN (SELECT id FROM menume.menu WHERE menu.id = id))))")
    List<String> dietaryRestrictions

    enum CostLevel {
        LOW(0), MEDIUM(1), HIGH(2)

        int value

        CostLevel(int value){
            this.value = value
        }

        static CostLevel of(int value){
            for(costLevel in values()){
                if(costLevel.value == value){
                    return costLevel
                }
            }
            return null
        }
    }

    enum FoodCategory {
        CHINESE, BBQ, PIZZA, ITALIAN, MISCELLANEOUS, AMERICAN, MEXICAN, THAI
    }
}
