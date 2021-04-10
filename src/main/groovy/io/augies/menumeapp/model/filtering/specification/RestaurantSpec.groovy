package io.augies.menumeapp.model.filtering.specification

import io.augies.menumeapp.model.Restaurant
import io.augies.menumeapp.model.filtering.FilterType
import io.augies.menumeapp.model.filtering.SearchCriteria
import org.springframework.data.jpa.domain.Specification

import javax.persistence.criteria.*

/**
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
 * @param < T >  the type of object that is filtered
 */
class RestaurantSpec implements Specification<Restaurant> {
    SearchCriteria criteria

    @Override
    Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Path<String> field = root.<String> get(criteria.field)

        switch (criteria.filterType) {
            case FilterType.GREATER:
                return cb.greaterThan(field, criteria.getValue().toString())
            case FilterType.LESS:
                return cb.lessThan(field, criteria.getValue().toString())
            case FilterType.EQUAL:
                return cb.equal(field, criteria.getValue().toString())
            case FilterType.GREATER_OR_EQUAL:
                return cb.greaterThanOrEqualTo(field, criteria.value.toString())
            case FilterType.LESS_OR_EQUAL:
                return cb.lessThanOrEqualTo(field, criteria.value.toString())
            case FilterType.NOT_EQUAL:
                return cb.notEqual(field, criteria.value.toString())
            case FilterType.CONTAINS:
                return cb.like(field, criteria.value.toString())
        }

        return null
    }
}
