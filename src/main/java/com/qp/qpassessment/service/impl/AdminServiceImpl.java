package com.qp.qpassessment.service.impl;

import com.qp.qpassessment.entity.GroceryItem;
import com.qp.qpassessment.entity.GroceryItemInventory;
import com.qp.qpassessment.exception.MyCustomException;
import com.qp.qpassessment.model.GroceryItemDTO;
import com.qp.qpassessment.model.GroceryItemInventoryDTO;
import com.qp.qpassessment.repository.GroceryItemInventoryRepository;
import com.qp.qpassessment.repository.GroceryItemRepository;
import com.qp.qpassessment.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired private GroceryItemRepository groceryItemRepository;
  @Autowired private GroceryItemInventoryRepository groceryItemInventoryRepository;

  @Override
  public GroceryItem addUpdateGroceryItem(GroceryItemDTO groceryItemDTO) throws MyCustomException {
    GroceryItem groceryItem = new GroceryItem();
    if (!Objects.isNull(groceryItemDTO.getId())) {
      Optional<GroceryItem> existingItem = groceryItemRepository.findById(groceryItemDTO.getId());
      if (existingItem.isEmpty()) {
        throw new MyCustomException("Grocery item for given id not present");
      }
      groceryItem = existingItem.get();
    }
    BeanUtils.copyProperties(groceryItemDTO, groceryItem, "id");
    return groceryItemRepository.save(groceryItem);
  }

  @Override
  public List<GroceryItem> getAllGroceryItems() {
    return groceryItemRepository.findAll();
  }

  @Override
  @Transactional
  public void deleteGroceryItem(int id) {
    groceryItemInventoryRepository.deleteByGroceryItemId(id);
    groceryItemRepository.deleteById(id);
  }

  @Override
  public GroceryItemInventory getInventoryDetailsForItem(int itemId) throws MyCustomException {
    if(!groceryItemRepository.existsById(itemId)){
      throw new MyCustomException("Invalid grocery item id");
    }
    return groceryItemInventoryRepository
        .findByGroceryItemId(itemId)
        .orElse(
            GroceryItemInventory.builder()
                .groceryItemId(itemId)
                .totalQuantity(0L)
                .usedQuantity(0L)
                .build());
  }

  @Override
  public GroceryItemInventory updateInventoryDetailsForItem(
      int itemId, GroceryItemInventoryDTO groceryItemInventoryDTO) throws MyCustomException {
    if(!groceryItemRepository.existsById(itemId)){
      throw new MyCustomException("Invalid grocery item id");
    }
    GroceryItemInventory groceryItemInventory =
        groceryItemInventoryRepository
            .findByGroceryItemId(itemId)
            .orElse(GroceryItemInventory.builder().groceryItemId(itemId).build());
    BeanUtils.copyProperties(groceryItemInventoryDTO, groceryItemInventory);
    return groceryItemInventoryRepository.save(groceryItemInventory);
  }
}
