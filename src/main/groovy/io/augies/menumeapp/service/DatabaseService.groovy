package io.augies.menumeapp.service

import groovy.util.logging.Slf4j
import io.augies.menumeapp.model.*
import io.augies.menumeapp.model.Restaurant.CostLevel
import io.augies.menumeapp.model.Restaurant.FoodCategory
import io.augies.menumeapp.model.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@Slf4j
class DatabaseService {
    @Autowired
    RestaurantRepository restaurantRepository
    @Autowired
    MenuRepository menuRepository
    @Autowired
    ItemRepository itemRepository
    @Autowired
    DietaryRestrictionRepository dietaryRestrictionRepository
    @Autowired
    ItemRestrictionRepository itemRestrictionRepository

    ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll().collect({it})
        if (restaurantList.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(restaurantList)
    }

    ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menuList = menuRepository.findAll()
        if (menuList.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(menuList)
    }

    ResponseEntity<List<Item>> getAllItems() {
        List<Item> itemList = itemRepository.findAll()
        if (itemList.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(itemList)
    }

    ResponseEntity<List<DietaryRestriction>> getAllDietaryRestrictions(){
        List<DietaryRestriction> dietaryRestrictionList = dietaryRestrictionRepository.findAll()
        if(dietaryRestrictionList.isEmpty()){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(dietaryRestrictionList)
    }

    ResponseEntity<List<ItemRestriction>> getAllItemRestrictions(){
        List<ItemRestriction> itemRestrictionList = itemRestrictionRepository.findAll()
        if(itemRestrictionList.isEmpty()){
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(itemRestrictionList)
    }

    ResponseEntity<List<Restaurant>> getRestaurants(
            List<Long> dietaryRestrictionIds,
            String name,
            CostLevel costLevel,
            Double distance,
            FoodCategory foodCategory,
            Menu.MealTime mealTime
    ){
        List<Restaurant> restaurants = restaurantRepository.findAll()
        if(dietaryRestrictionIds) restaurants = restaurantRepository.findByDietaryRestrictions(dietaryRestrictionIds, restaurants)
        if(name) restaurants = restaurantRepository.findByRestaurantsByName(name, restaurants)
        if(costLevel) restaurants = restaurantRepository.findByRestaurantsByCost(costLevel.value, restaurants)
        if(distance) restaurants = restaurantRepository.findByRestaurantsByDistance(distance, restaurants)
        if(foodCategory) restaurants = restaurantRepository.findByRestaurantsByCategory(foodCategory, restaurants)
        if(mealTime) restaurants = restaurantRepository.findByRestaurantsByMealTime(mealTime, restaurants)
        if(dietaryRestrictionIds || name || costLevel || distance || foodCategory || mealTime) {
            for (restaurant in restaurants) {
                for (int i = 0; i < restaurant.menus.size(); i++) {
                    Menu menu = restaurant.menus[i]
                    if(dietaryRestrictionIds){
                        for (int j = 0; j < menu.items.size(); j++) { //for each item
                            Item item = menu.items[j]
                            if (Collections.disjoint(item.itemRestrictions, itemRestrictionRepository.findAllById(dietaryRestrictionIds))) {
                                menu.items.remove(item)
                                j--
                            }
                        }
                        if (menu.items.isEmpty()) {
                            restaurant.menus.remove(menu)
                            i--
                        }

                    }
                    if(mealTime && menu.mealTime!=mealTime){
                        restaurant.menus.remove(menu)
                        i--
                    }
                }
            }
        }
        return ResponseEntity.ok(restaurants)
    }
}
