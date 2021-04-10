package io.augies.menumeapp.service

import io.augies.menumeapp.model.DietaryRestriction
import io.augies.menumeapp.model.Item
import io.augies.menumeapp.model.ItemRestriction
import io.augies.menumeapp.model.Menu
import io.augies.menumeapp.model.Restaurant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MenuMeApiService {
    @Autowired
    DatabaseService databaseService

    @GetMapping("/restaurants")
    ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(required = false) String search) {
        databaseService.searchRestaurants(search)
    }

    @PostMapping("/restaurants/create")
    ResponseEntity<String> createRestaurant(@RequestBody Restaurant restaurant) {
        databaseService.addRestaurant(restaurant)
    }

    @PostMapping("/menus/create")
    ResponseEntity<String> createMenu(@RequestBody Menu menu) {
        databaseService.addMenu(menu)
    }

    @PostMapping("/items/create")
    ResponseEntity<String> createItem(@RequestBody Item item) {
        databaseService.addItem(item)
    }

    @PostMapping("/dietary-restrictions/create")
    ResponseEntity<String> createDietaryRestriction(@RequestBody DietaryRestriction dietaryRestriction) {
        databaseService.addDietaryRestriction(dietaryRestriction)
    }

    @PostMapping("/item-restrictions/create")
    ResponseEntity<String> createItemRestriction(@RequestBody ItemRestriction itemRestriction) {
        databaseService.addItemRestriction(itemRestriction)
    }
}
