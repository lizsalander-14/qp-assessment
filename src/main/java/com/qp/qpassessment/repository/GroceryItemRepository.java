package com.qp.qpassessment.repository;

import com.qp.qpassessment.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {

  @Query(
      "SELECT item FROM GroceryItem item RIGHT JOIN GroceryItemInventory inv ON "
          + "item.id=inv.groceryItemId WHERE inv.totalQuantity>inv.usedQuantity")
  public List<GroceryItem> getAvailableGroceryItems();
}
