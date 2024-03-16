package com.qp.qpassessment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItemInventoryDTO {

  private long totalQuantity;

  private long usedQuantity;
}
