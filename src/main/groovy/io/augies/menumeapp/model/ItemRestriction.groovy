package io.augies.menumeapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'ItemRestriction')
class ItemRestriction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @ManyToOne
    @JoinColumn(name = 'itemId', nullable = false)
    Long itemId

    @ManyToOne
    @JoinColumn(name = 'dietaryRestrictionId', nullable = false)
    Long dietaryId
}
