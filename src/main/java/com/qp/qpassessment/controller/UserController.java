package com.qp.qpassessment.controller;

import com.qp.qpassessment.entity.UserOrder;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemOrderDTO;
import com.qp.qpassessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping("/product-list")
  public ResponseEntity<List<GroceryItemDTO>> getAvailableGroceryItems() {
    return new ResponseEntity<>(userService.getAvailableGroceryItems(), HttpStatus.OK);
  }

  @PostMapping("/order/products")
  public ResponseEntity<UserOrder> orderProducts(
      @RequestParam("username") String userName,
      @RequestBody List<GroceryItemOrderDTO> groceryItemList)
      throws MyCustomException {
    return new ResponseEntity<>(
        userService.placeProductsOrder(userName, groceryItemList), HttpStatus.OK);
  }
}
