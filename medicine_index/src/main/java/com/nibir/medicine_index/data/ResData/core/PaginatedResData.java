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
public class PaginatedResData<T> {
    @JsonProperty("data")
    private PaginationBaseData<?> data;

    @JsonProperty("request")
    private PaginationRequestBaseData request;

}