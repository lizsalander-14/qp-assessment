package com.qp.qpassessment.service;

import com.qp.qpassessment.entity.GroceryItem;
import com.qp.qpassessment.entity.GroceryItemInventory;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemInventoryDTO;

import java.util.List;

public interface AdminService {

  GroceryItem addUpdateGroceryItem(GroceryItemDTO groceryItemDTO) throws MyCustomException;

  List<GroceryItem> getAllGroceryItems();

  void deleteGroceryItem(int id);

  GroceryItemInventory getInventoryDetailsForItem(int itemId) throws MyCustomException;

  GroceryItemInventory updateInventoryDetailsForItem(
      int itemId, GroceryItemInventoryDTO groceryItemInventoryDTO) throws MyCustomException;
}
