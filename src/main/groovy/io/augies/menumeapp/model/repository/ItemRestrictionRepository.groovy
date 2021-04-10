package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRestrictionRepository extends JpaRepository<Item, Long> {

}