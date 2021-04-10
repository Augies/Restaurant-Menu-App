package io.augies.menumeapp.service

import io.augies.menumeapp.model.Restaurant
import io.augies.menumeapp.model.repository.DietaryRestrictionRepository
import io.augies.menumeapp.model.repository.ItemRepository
import io.augies.menumeapp.model.repository.ItemRestrictionRepository
import io.augies.menumeapp.model.repository.MenuRepository
import io.augies.menumeapp.model.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
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

    List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll()
    }
}
