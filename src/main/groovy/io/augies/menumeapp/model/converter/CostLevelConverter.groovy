package io.augies.menumeapp.model.converter

import io.augies.menumeapp.model.Restaurant

import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * https://www.baeldung.com/jpa-persisting-enums-in-jpa
 */
@Converter(autoApply = true)
class CostLevelConverter implements AttributeConverter<Restaurant.CostLevel, Integer>{

    @Override
    Integer convertToDatabaseColumn(Restaurant.CostLevel attribute) {
        return attribute.value
    }

    @Override
    Restaurant.CostLevel convertToEntityAttribute(Integer dbData) {
        return Restaurant.CostLevel.of(dbData)
    }
}
