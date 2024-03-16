package com.qp.qpassessment.repository;

import com.qp.qpassessment.entity.GroceryItemInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroceryItemInventoryRepository extends JpaRepository<GroceryItemInventory, Integer> {

  void deleteByGroceryItemId(int groceryItemId);

  Optional<GroceryItemInventory> findByGroceryItemId(int groceryItemId);

  List<GroceryItemInventory> findByGroceryItemIdIn(List<Integer> groceryItemIds);
}
