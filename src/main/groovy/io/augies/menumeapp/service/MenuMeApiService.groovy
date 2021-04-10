package io.augies.menumeapp.service

import io.augies.menumeapp.model.DietaryRestriction
import io.augies.menumeapp.model.Item
import io.augies.menumeapp.model.ItemRestriction
import io.augies.menumeapp.model.Menu
import io.augies.menumeapp.model.Restaurant
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
    ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam(required = false) String search) {
        databaseService.searchRestaurants(search)
    }

    @GetMapping("/menus")
    ResponseEntity<List<Menu>> getMenus(@RequestParam(required=false)String search){
        databaseService.searchMenus(search)
    }

    @GetMapping("/items")
    ResponseEntity<List<Item>> getItems(@RequestParam(required = false)String search){
        databaseService.searchItems(search)
    }

    @GetMapping("/dietary-restrictions")
    ResponseEntity<List<DietaryRestriction>> getDietaryRestrictions(@RequestParam(required=false)String search){
        databaseService.searchDietaryRestrictions(search)
    }

    @GetMapping("/item-restrictions")
    ResponseEntity<List<ItemRestriction>> getItemRestrictions(@RequestParam(required=false)String search){
        databaseService.searchItemRestrictions(search)
    }
}
