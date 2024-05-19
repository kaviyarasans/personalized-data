package com.niq.shoppers.data.repository.specification;

import static org.springframework.data.jpa.domain.Specification.where;

import com.niq.shoppers.data.entity.Product;
import com.niq.shoppers.data.repository.specification.filter.Filter;
import com.niq.shoppers.data.repository.specification.filter.QueryOperator;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> getSpecificationFromFilters(List<Filter> filter) {
        Specification<Product> specification = where(createSpecification(filter.remove(0)));
        for (Filter input : filter) {
            specification = specification.and(createSpecification(input));
        }
        return specification;
    }

    private static Specification<Product> createSpecification(Filter input) {
        switch (input.getOperator()) {
            case EQUALS -> {
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                        criteriaBuilder.upper(root.get(input.getField())),
                        input.getValue().toUpperCase());
            }
            case IN -> {
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(input.getField())).value(input.getValues());
            }
            default -> throw new RuntimeException("Operation not supported yet");
        }
    }

    public static Filter createInOperatorFilter(String fieldName, List<String> valueList) {
        return Filter.builder()
                .field(fieldName)
                .operator(QueryOperator.IN)
                .values(valueList)
                .build();
    }

    public static Filter createEqualsOperatorFilter(String fieldName, String fieldValue) {
        return Filter.builder()
                .field(fieldName)
                .operator(QueryOperator.EQUALS)
                .value(fieldValue)
                .build();
    }
}
