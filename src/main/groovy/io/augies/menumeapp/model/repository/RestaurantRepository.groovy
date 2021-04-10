package io.augies.menumeapp.model.repository

import io.augies.menumeapp.model.Menu.MealTime
import io.augies.menumeapp.model.Restaurant
import io.augies.menumeapp.model.Restaurant.FoodCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

interface RestaurantRepository extends
        JpaRepository<Restaurant, Long>,
        JpaSpecificationExecutor<Restaurant> {

    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r IN(SELECT m.restaurant FROM Menu m WHERE m IN(SELECT i.menu FROM Item i WHERE i IN(SELECT ir.item FROM ItemRestriction ir WHERE ir.dietaryRestriction IN (SELECT dr FROM DietaryRestriction dr WHERE dr.id IN :dietaryRestrictionIds))))")
    List<Restaurant> findByDietaryRestrictions(@Param("dietaryRestrictionIds") Collection<Long> dietaryRestrictionIds, @Param("restaurantsList") List<Restaurant> restaurantsList)

    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r IN (SELECT r2 FROM Restaurant r2 WHERE r2.name = :name)")
    List<Restaurant> findByRestaurantsByName(@Param("name") String name, @Param("restaurantsList") List<Restaurant> restaurantsList)

    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r.costLevelValue <= :cost")
    List<Restaurant> findByRestaurantsByCost(@Param("cost") Integer cost, @Param("restaurantsList") List<Restaurant> restaurantsList)
    
    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r.distance <= :distance")
    List<Restaurant> findByRestaurantsByDistance(@Param("distance") Double distance, @Param("restaurantsList") List<Restaurant> restaurantsList)
    
    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r.foodCategory = :category")
    List<Restaurant> findByRestaurantsByCategory(@Param("category")FoodCategory category, @Param("restaurantsList") List<Restaurant> restaurantList)
    
    @Query(value = "SELECT r FROM Restaurant r WHERE r IN :restaurantsList AND r IN (SELECT m.restaurant FROM Menu m WHERE m.mealTime = :mealTime)")
    List<Restaurant> findByRestaurantsByMealTime(@Param("mealTime")MealTime mealTime, @Param("restaurantsList") List<Restaurant> restaurantsList)

    @PersistenceContext
    EntityManager entityManager
}