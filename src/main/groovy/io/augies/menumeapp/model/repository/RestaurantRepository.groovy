package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Restaurant
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}