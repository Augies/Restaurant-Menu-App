package io.augies.menumeapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    Menu menu
    Double cost
    Double calories
    @OneToMany(mappedBy = 'item')
    Set<ItemRestriction> itemRestrictions
}
