package com.qp.qpassessment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "GROCERY_ITEM_INVENTORY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GroceryItemInventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "GROCERY_ITEM_ID")
  private int groceryItemId;

  @Column(name = "TOTAL_QUANTITY")
  private long totalQuantity;

  @Column(name = "USED_QUANTITY")
  private long usedQuantity;

  @Column(name = "CREATED_ON")
  @CreationTimestamp
  private Timestamp createdOn;

  @Column(name = "UPDATED_ON")
  @UpdateTimestamp
  private Timestamp updatedOn;
}
