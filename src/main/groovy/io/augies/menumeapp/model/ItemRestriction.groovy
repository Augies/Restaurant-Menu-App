package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'itemrestriction')
@ToString(includeNames = true)
class ItemRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @ManyToOne
    @JoinColumn(name = 'itemId', nullable = false)
    Item item

    @ManyToOne
    @JoinColumn(name = 'dietaryRestrictionId', nullable = false)
    DietaryRestriction dietaryRestriction
}
