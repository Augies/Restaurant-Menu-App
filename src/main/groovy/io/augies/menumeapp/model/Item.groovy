package io.augies.menumeapp.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'Item')
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
    String description
    @ManyToOne
    @JoinColumn(name = 'menuId', nullable=false)
    Long menuId
    Double cost
    Double calories
}
