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

    ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = restaurantRepository.findAll()
        if (restaurantList.isEmpty()) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(restaurantList)
    }

    List<Menu> getAllMenus() {
        return menuRepository.findAll()
    }

    List<Item> getAllItems() {
        return itemRepository.findAll()
    }

    ResponseEntity<String> addRestaurant(Restaurant restaurant) {
        try {
            restaurantRepository.save(restaurant)
            log.info("Saving restaurant: $restaurant")
        } catch (RuntimeException e) {
            log.error("An exception occurred while creating a restaurant: ", e)
            return ResponseEntity.badRequest().body("Failed to create restaurant")
        }
        return ResponseEntity.ok("Restaurant saved.")
    }

    ResponseEntity<String> addMenu(Menu menu) {
        try {
            menuRepository.save(menu)
            log.info("Saving menu: $menu")
        } catch (RuntimeException e) {
            log.error("An exception occurred while creating a menu: ", e)
            return ResponseEntity.badRequest().body("Failed to create Menu")
        }
        return ResponseEntity.ok("Menu saved.")
    }

    ResponseEntity<String> addItem(Item item) {
        try {
            itemRepository.save(item)
            log.info("Saving item: $item")
        } catch (RuntimeException e) {
            log.error("An exception occurred while creating an item: ", e)
            return ResponseEntity.badRequest().body("Failed to create Item")
        }
        return ResponseEntity.ok("Item saved.")
    }

    ResponseEntity<String> addDietaryRestriction(DietaryRestriction dietaryRestriction) {
        try {
            dietaryRestrictionRepository.save(dietaryRestriction)
            log.info("Saving dietaryRestriction: $dietaryRestriction")
        } catch (RuntimeException e) {
            log.error("An exception occurred while creating a dietary restriction:", e)
            return ResponseEntity.badRequest().body("Failed to create Dietary Restriction")
        }
        return ResponseEntity.ok("Dietary Restriction saved.")
    }

    ResponseEntity<String> addItemRestriction(ItemRestriction itemRestriction) {
        try {
            itemRestrictionRepository.save(itemRestriction)
            log.info("Saving item restriction: $itemRestriction")
        } catch (RuntimeException e) {
            log.error("An exception occurred while creating a item restriction:", e)
            return ResponseEntity.badRequest().body("Failed to create Item Restriction")
        }
        return ResponseEntity.ok("Item Restriction saved.")
    }
}
