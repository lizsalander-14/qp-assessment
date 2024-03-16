SQL queries for entities used

- Grocery Item
  CREATE TABLE GROCERY_ITEM (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(250) NOT NULL,
  `PRICE` float NOT NULL,
  `CREATED_ON` datetime DEFAULT current_timestamp(),
  `UPDATED_ON` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_product_name` (`NAME`)
  );
  
- Grocery Item Inventory
  CREATE TABLE GROCERY_ITEM_INVENTORY (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `GROCERY_ITEM_ID` int(10) unsigned NOT NULL,
  `TOTAL_QUANTITY` long NOT NULL DEFAULT 0,
  `USED_QUANTITY` long NOT NULL DEFAULT 0,
  `CREATED_ON` datetime DEFAULT current_timestamp(),
  `UPDATED_ON` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uk_product_id` (`GROCERY_ITEM_ID`),
  FOREIGN KEY (`GROCERY_ITEM_ID`) REFERENCES GROCERY_ITEM(`ID`)
  );
  
- User Order
  CREATE TABLE USER_ORDER(
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL,
  `ORDER_DETAILS` text NOT NULL,
  `CREATED_ON` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ID`)
  );