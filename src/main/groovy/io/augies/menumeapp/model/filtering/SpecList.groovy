package io.augies.menumeapp.model.filtering

import org.springframework.data.jpa.domain.Specification

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

/**
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications
 */
class SpecList<T extends Comparable<? super T>> implements Specification<SpecList> {
    List<SpecItem<T>> specItemList

    @Override
    Predicate toPredicate(Root<SpecList> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>()

        for(specItem in specItemList){
            switch(specItem.filterType){
                case FilterType.GREATER:
                    predicates.add(
                            criteriaBuilder.greaterThan(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.LESS:
                    predicates.add(
                            criteriaBuilder.lessThan(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.EQUAL:
                    predicates.add(
                            criteriaBuilder.equal(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.GREATER_OR_EQUAL:
                    predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.LESS_OR_EQUAL:
                    predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.NOT_EQUAL:
                    predicates.add(
                            criteriaBuilder.notEqual(
                                    root.get(specItem.field),
                                    specItem.value
                            )
                    )
                    break
                case FilterType.CONTAINS:
                    predicates.add(
                            criteriaBuilder.notEqual(
                                    root.get(specItem.field),
                                    "%$specItem.value%"
                            )
                    )
                    break
            }
        }

        Predicate
    }
}
