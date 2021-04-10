package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.*

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
