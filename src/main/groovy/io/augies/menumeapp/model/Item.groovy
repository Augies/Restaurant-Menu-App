package io.augies.menumeapp.model

import groovy.transform.ToString

import javax.persistence.*

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
    @JoinColumn(name = 'menuId', nullable = false)
    Menu menu
    Double cost
    Double calories
}
