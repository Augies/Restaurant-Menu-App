package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository extends JpaRepository<Menu, Long> {
}
