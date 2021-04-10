package io.augies.menumeapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'DietaryRestriction')
class DietaryRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
}
