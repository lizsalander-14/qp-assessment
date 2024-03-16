package com.qp.qpassessment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemDTO {

  private Integer id;

  private String productName;

  private float price;
}
