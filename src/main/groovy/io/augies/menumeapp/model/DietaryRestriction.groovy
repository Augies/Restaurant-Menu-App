package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'dietaryrestriction')
@ToString(includeNames = true)
class DietaryRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name
}
