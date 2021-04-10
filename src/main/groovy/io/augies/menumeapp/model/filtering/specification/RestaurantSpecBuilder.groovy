package io.augies.menumeapp.model.filtering.specification

import io.augies.menumeapp.model.Restaurant
import io.augies.menumeapp.model.filtering.FilterType
import io.augies.menumeapp.model.filtering.SearchCriteria
import org.springframework.data.jpa.domain.Specification

import java.util.stream.Collectors

/**
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
 * @param <T> the type of the object being filtered
 */
class RestaurantSpecBuilder {
    List<SearchCriteria> params = new ArrayList<>()

    RestaurantSpecBuilder with(String field, FilterType filterType, Object value){
        params.add(new SearchCriteria(field: field, value: value, filterType: filterType))
        return this
    }

    Specification<Restaurant> build(){
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(RestaurantSpec.&new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result) & (specs.get(i));
        }
        return result;
    }
}
