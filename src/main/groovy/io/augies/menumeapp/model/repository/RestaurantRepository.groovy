package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

interface RestaurantRepository extends
        JpaRepository<Restaurant, Long>,
        JpaSpecificationExecutor<Restaurant> {

    @PersistenceContext
    EntityManager entityManager
}