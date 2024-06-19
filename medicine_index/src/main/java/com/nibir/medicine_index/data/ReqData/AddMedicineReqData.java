package com.nibir.medicine_index.data.ReqData;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddMedicineReqData {
    private Long id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Generic name must not be empty")
    private String genericName;

    @NotBlank(message = "Price must not be empty")
    private String price;

    @NotBlank(message = "Batch Number must not be empty")
    private String batchNo;
    private String otherDetails;

    private Long manufacturerId;
    private String manufacturer;

}
