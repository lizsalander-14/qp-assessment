package com.qp.qpassessment.service.impl;

import com.qp.qpassessment.entity.GroceryItem;
import com.qp.qpassessment.entity.GroceryItemInventory;
import com.qp.qpassessment.entity.UserOrder;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemOrderDTO;
import com.qp.qpassessment.repository.GroceryItemInventoryRepository;
import com.qp.qpassessment.repository.GroceryItemRepository;
import com.qp.qpassessment.repository.UserOrderRepository;
import com.qp.qpassessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private GroceryItemRepository groceryItemRepository;
  @Autowired private GroceryItemInventoryRepository groceryItemInventoryRepository;
  @Autowired private UserOrderRepository userOrderRepository;

  @Override
  public List<GroceryItemDTO> getAvailableGroceryItems() {
    List<GroceryItem> groceryItems = groceryItemRepository.getAvailableGroceryItems();
    return groceryItems.stream()
        .map(
            item ->
                GroceryItemDTO.builder()
                    .id(item.getId())
                    .productName(item.getProductName())
                    .price(item.getPrice())
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public UserOrder placeProductsOrder(String username, List<GroceryItemOrderDTO> groceryItemList)
      throws MyCustomException {
    List<GroceryItemInventory> itemInventories =
        groceryItemInventoryRepository.findByGroceryItemIdIn(
            groceryItemList.stream()
                .map(GroceryItemOrderDTO::getItemId)
                .collect(Collectors.toList()));
    Map<Integer, GroceryItemInventory> groceryItemInventoryMap =
        itemInventories.stream()
            .collect(Collectors.toMap(GroceryItemInventory::getGroceryItemId, Function.identity()));
    for (GroceryItemOrderDTO item : groceryItemList) {
      GroceryItemInventory itemInventory = groceryItemInventoryMap.get(item.getItemId());
      if (Objects.isNull(itemInventory)
          || item.getQuantity()
              > (itemInventory.getTotalQuantity() - itemInventory.getUsedQuantity())) {
        throw new MyCustomException("Item unavailable for itemId " + item.getItemId());
      }
      itemInventory.setUsedQuantity(itemInventory.getUsedQuantity() + item.getQuantity());
    }
    return saveOrderDetails(
        itemInventories,
        UserOrder.builder()
            .username(username)
            .orderDetails(String.valueOf(groceryItemList))
            .build());
  }

  @Transactional
  private UserOrder saveOrderDetails(
      List<GroceryItemInventory> updatedInventory, UserOrder userOrder) {
    groceryItemInventoryRepository.saveAll(updatedInventory);
    return userOrderRepository.save(userOrder);
  }
}
