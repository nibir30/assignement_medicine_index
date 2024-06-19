package com.nibir.medicine_index.data.ResData.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequestBaseData {
    @JsonProperty("perPage")
    private Integer perPage;

    @JsonProperty("currentPage")
    private Integer currentPage;

    @JsonProperty("sortBy")
    private String sortBy;

    @JsonProperty("sortType")
    private String sortType;

    @JsonProperty("search")
    private String search;

}