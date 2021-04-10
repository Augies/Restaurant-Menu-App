package io.augies.menumeapp.model.repository


import io.augies.menumeapp.model.ItemRestriction
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRestrictionRepository extends JpaRepository<ItemRestriction, Long> {

}