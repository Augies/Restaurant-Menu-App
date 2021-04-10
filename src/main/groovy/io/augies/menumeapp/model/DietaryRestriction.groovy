package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = 'dietaryrestriction')
@ToString(includeNames = true)
class DietaryRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name
}
