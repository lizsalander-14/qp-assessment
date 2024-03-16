package com.qp.qpassessment.service;

import com.qp.qpassessment.entity.UserOrder;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemOrderDTO;

import java.util.List;

public interface UserService {

  List<GroceryItemDTO> getAvailableGroceryItems();

  UserOrder placeProductsOrder(String username, List<GroceryItemOrderDTO> groceryItemList)
      throws MyCustomException;
}
