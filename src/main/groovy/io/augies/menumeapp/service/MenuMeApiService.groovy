package io.augies.menumeapp.service

import io.augies.menumeapp.model.Restaurant
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MenuMeApiService {

    @GetMapping("/testRequest")
    ResponseEntity<Restaurant> testRequest(){
        return ResponseEntity.ok(new Restaurant(
                name: "testRestaurant",
                location: "Lincoln, NE",
                imageUrl: "https://cdn.hcbrands.com/media/catalog/product/cache/5/image/600x600/9df78eab33525d08d6e5fb8d27136e95/7/6/7620-swag-hashtag-rubber-stamp-hcb.png",
                websiteUrl: "https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstleyVEVO",
                costLevel: Restaurant.CostLevel.HIGH,
                foodCategory: Restaurant.FoodCategory.MEXICAN
        ))
    }
}
