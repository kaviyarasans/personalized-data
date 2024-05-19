package com.niq.shoppers.data.repository.specification.filter;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {
    private String field;

    private QueryOperator operator;

    private String value;

    private List<String> values;
}
