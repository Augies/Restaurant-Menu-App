package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository extends JpaRepository<Item, Long> {

}