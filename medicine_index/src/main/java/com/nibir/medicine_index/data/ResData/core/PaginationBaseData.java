package com.nibir.medicine_index.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationBaseData<T> {
    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("totalItems")
    private Long totalItems;

    @JsonProperty("currentPage")
    private int currentPage;

    @JsonProperty("listResponse")
    private T listResponse;
}
