package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = 'item')
@ToString(includeNames = true)
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String name
    String description
    @ManyToOne
    @JoinColumn(name = 'menuId', nullable=false)
    Menu menu
    Double cost
    Double calories
}
