package com.qp.qpassessment.controller;

import com.qp.qpassessment.entity.GroceryItem;
import com.qp.qpassessment.entity.GroceryItemInventory;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemInventoryDTO;
import com.qp.qpassessment.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

  @Autowired private AdminService adminService;

  @PostMapping("/grocery-item")
  public ResponseEntity<GroceryItem> addUpdateGroceryItem(
      @RequestBody GroceryItemDTO groceryItemDTO) throws MyCustomException {
    return new ResponseEntity<>(adminService.addUpdateGroceryItem(groceryItemDTO), HttpStatus.OK);
  }

  @GetMapping("/grocery-items")
  public ResponseEntity<List<GroceryItem>> getAllGroceryItems() {
    return new ResponseEntity<>(adminService.getAllGroceryItems(), HttpStatus.OK);
  }

  @DeleteMapping("/grocery-item/{id}")
  public ResponseEntity deleteGroceryItem(@PathVariable("id") int id) {
    adminService.deleteGroceryItem(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/grocery-inventory/{itemId}")
  public ResponseEntity<GroceryItemInventory> getInventoryDetailsForItem(
      @PathVariable("itemId") int itemId) throws MyCustomException {
    return new ResponseEntity<>(adminService.getInventoryDetailsForItem(itemId), HttpStatus.OK);
  }

  @PutMapping("/grocery-inventory/{itemId}")
  public ResponseEntity<GroceryItemInventory> updateInventoryDetailsForItem(
      @PathVariable("itemId") int itemId,
      @RequestBody GroceryItemInventoryDTO groceryItemInventoryDTO) throws MyCustomException {
    return new ResponseEntity<>(
        adminService.updateInventoryDetailsForItem(itemId, groceryItemInventoryDTO), HttpStatus.OK);
  }
}
