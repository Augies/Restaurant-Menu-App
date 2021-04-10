package io.augies.menumeapp.service

import groovy.util.logging.Slf4j
import io.augies.menumeapp.model.*
import io.augies.menumeapp.model.filtering.FilterType
import io.augies.menumeapp.model.filtering.specification.RestaurantSpecBuilder
import io.augies.menumeapp.model.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

import java.util.regex.Matcher
import java.util.regex.Pattern

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

    /**
     * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
     * @param search the search string
     * @return restaurants matching the search
     */
    ResponseEntity<List<Restaurant>> searchRestaurants(String search) {
        if (!search) return getAllRestaurants()
        RestaurantSpecBuilder builder = new RestaurantSpecBuilder()
        Pattern pattern = Pattern.compile("(\\w+?)(<>|<:|>:|!:|:|<|>)(\\w+?),")
        Matcher matcher = pattern.matcher(search + ",")
        while (matcher.find()) {
            FilterType filterType = FilterType.getBySymbol(matcher.group(2))
            String value = matcher.group(3)
            if (filterType == FilterType.CONTAINS) {
                value = "%$value%"
            }
            builder.with(matcher.group(1), filterType, value)
        }
        List<Restaurant> restaurantList = restaurantRepository.findAll(builder.build())
        if (restaurantList.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(restaurantList)
    }

    ResponseEntity<List<Menu>> searchMenus(String search){
        //TODO
        if(!search) return getAllMenus()
        return null
    }

    ResponseEntity<List<Item>> searchItems(String search){
        //TODO
        if(!search) return getAllItems()
        return null
    }

    ResponseEntity<List<DietaryRestriction>> searchDietaryRestrictions(String search){
        //TODO
        if(!search) return getAllDietaryRestrictions()
        return null
    }

    ResponseEntity<List<ItemRestriction>> searchItemRestrictions(String search){
        //TODO
        if(!search) return getAllItemRestrictions()
        return null
    }

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
}
