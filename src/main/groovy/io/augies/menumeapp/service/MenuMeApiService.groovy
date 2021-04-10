package io.augies.menumeapp.service

import io.augies.menumeapp.model.DietaryRestriction
import io.augies.menumeapp.model.Item
import io.augies.menumeapp.model.ItemRestriction
import io.augies.menumeapp.model.Menu
import io.augies.menumeapp.model.Menu.MealTime
import io.augies.menumeapp.model.Restaurant
import io.augies.menumeapp.model.Restaurant.FoodCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MenuMeApiService {
    @Autowired
    DatabaseService databaseService

    @GetMapping("/restaurants")
    ResponseEntity<List<Restaurant>> getRestaurants(
            @RequestParam(required=false) List<Long> dietaryRestrictionIds,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) Restaurant.CostLevel costLevel,
            @RequestParam(required=false) Double distance,
            @RequestParam(required=false) FoodCategory foodCategory,
            @RequestParam(required=false) MealTime mealTime
    ){
        return databaseService.getRestaurants(dietaryRestrictionIds,name,costLevel,distance,foodCategory,mealTime)
    }

    @GetMapping("/menus")
    ResponseEntity<List<Menu>> getMenus(){
        databaseService.getAllMenus()
    }

    @GetMapping("/items")
    ResponseEntity<List<Item>> getItems(){
        databaseService.getAllItems()
    }

    @GetMapping("/dietary-restrictions")
    ResponseEntity<List<DietaryRestriction>> getDietaryRestrictions(){
        databaseService.getAllDietaryRestrictions()
    }

    @GetMapping("/item-restrictions")
    ResponseEntity<List<ItemRestriction>> getItemRestrictions(){
        databaseService.getAllItemRestrictions()
    }
}
