USE Groceries;

DROP TABLE IF EXISTS `Sales_Fact`;
DROP TABLE IF EXISTS `Product`;
-- ------------------------------------------------------------
-- --------------------------- Create Schema of PRODUCT table -
-- ------------------------------------------------------------

CREATE TABLE `Product` (
    `product_key` DOUBLE NOT NULL,
    `description` VARCHAR(255),
    `full_description` VARCHAR(50),
    `SKU_number` DOUBLE,
    `package_size` VARCHAR(255),
    `brand` VARCHAR(255),
    `subcategory` VARCHAR(255),
    `category` VARCHAR(255),
    `department` VARCHAR(255),
    `package_type` VARCHAR(255),
    `diet_type` VARCHAR(255),
    `weight` DOUBLE,
    `weight_unit_of_measure` VARCHAR(255),
    `units_per_retail_case` DOUBLE,
    `units_per_shipping_case` DOUBLE,
    `cases_per_pallet` DOUBLE,
    `shelf_width_cm` DOUBLE,
    `shelf_height_cm` DOUBLE,
    `shelf_depth_cm` DOUBLE,
    PRIMARY KEY (product_key)
); 

-- ------------------------------------------------------------
-- ----------------------------- Load data into PRODUCT table -
-- ------------------------------------------------------------


LOAD DATA LOCAL INFILE '/Users/evs/Desktop/GroceriesDB/Product.csv' 
INTO TABLE Product 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';

-- ------------------------------------------------------------
-- ---------------------- Modify PRODUCT table for assignment -
-- ------------------------------------------------------------


ALTER TABLE Product DROP COLUMN `description`;
ALTER TABLE Product DROP COLUMN `full_description`;
ALTER TABLE Product DROP COLUMN `SKU_number`;
ALTER TABLE Product DROP COLUMN `package_size`;
ALTER TABLE Product DROP COLUMN `brand`;
ALTER TABLE Product DROP COLUMN `package_type`;
ALTER TABLE Product DROP COLUMN `diet_type`;
ALTER TABLE Product DROP COLUMN `weight`;
ALTER TABLE Product DROP COLUMN `weight_unit_of_measure`;
ALTER TABLE Product DROP COLUMN `units_per_retail_case`;
ALTER TABLE Product DROP COLUMN `units_per_shipping_case`;
ALTER TABLE Product DROP COLUMN `cases_per_pallet`;
ALTER TABLE Product DROP COLUMN `shelf_width_cm`;
ALTER TABLE Product DROP COLUMN `shelf_height_cm`;
ALTER TABLE Product DROP COLUMN `shelf_depth_cm`;


-- ------------------------------------------------------------
-- ----------------------------- Create Schema of STORE table -
-- ------------------------------------------------------------

DROP TABLE IF EXISTS `Store`;

CREATE TABLE `Store` (
    `store_key` DOUBLE NOT NULL,
    `name` VARCHAR(255),
    `store_number` DOUBLE,
    `store_street_address` VARCHAR(255),
    `city` VARCHAR(255),
    `store_county` VARCHAR(255),
    `store_state` VARCHAR(255),
    `store_zip` DOUBLE,
    `sales_district` VARCHAR(255),
    `sales_region` VARCHAR(255),
    `store_manager` VARCHAR(255),
    `store_phone` VARCHAR(255),
    `store_FAX` VARCHAR(255),
    `floor_plan_type` VARCHAR(255),
    `photo_processing_type` VARCHAR(255),
    `finance_services_type` VARCHAR(255),
    `first_opened_date` DATETIME,
    `last_remodel_date` DATETIME,
    `store_sqft` DOUBLE,
    `grocery_sqft` DOUBLE,
    `frozen_sqft` DOUBLE,
    `meat_sqft` DOUBLE,
    PRIMARY KEY (store_key)
); 

-- ------------------------------------------------------------
-- ------------------------------- Load data into STORE table -
-- ------------------------------------------------------------

LOAD DATA LOCAL INFILE '/Users/evs/Desktop/GroceriesDB/Store.csv' 
INTO TABLE Store 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';

-- ------------------------------------------------------------
-- ------------------------ Modify STORE table for assignment -
-- ------------------------------------------------------------

ALTER TABLE Store DROP COLUMN `name`;
ALTER TABLE Store DROP COLUMN `store_number`;
ALTER TABLE Store DROP COLUMN `store_street_address`;
ALTER TABLE Store DROP COLUMN `store_county`;
ALTER TABLE Store DROP COLUMN `store_zip`;
ALTER TABLE Store DROP COLUMN `sales_district`;
ALTER TABLE Store DROP COLUMN `store_manager`;
ALTER TABLE Store DROP COLUMN `store_phone`;
ALTER TABLE Store DROP COLUMN `store_FAX`;
ALTER TABLE Store DROP COLUMN `floor_plan_type`;
ALTER TABLE Store DROP COLUMN `photo_processing_type`;
ALTER TABLE Store DROP COLUMN `finance_services_type`;
ALTER TABLE Store DROP COLUMN `first_opened_date`;
ALTER TABLE Store DROP COLUMN `last_remodel_date`;
ALTER TABLE Store DROP COLUMN `store_sqft`;
ALTER TABLE Store DROP COLUMN `grocery_sqft`;
ALTER TABLE Store DROP COLUMN `frozen_sqft`;
ALTER TABLE Store DROP COLUMN `meat_sqft`;


-- ------------------------------------------------------------
-- ----------------------------- Create Schema of Time  table -
-- ------------------------------------------------------------

DROP TABLE IF EXISTS `Time`;

CREATE TABLE `Time` (
    `time_key` DOUBLE NOT NULL,
    `date` DATETIME,
    `day_of_week` VARCHAR(255),
    `day_number_in_month` DOUBLE,
    `day_number_overall` DOUBLE,
    `week_number_in_year` DOUBLE,
    `week_number_overall` DOUBLE,
    `Month` VARCHAR(50),
    `quarter` DOUBLE,
    `fiscal_period` VARCHAR(255),
    `year` DOUBLE,
    `holiday_flag` VARCHAR(255),
    PRIMARY KEY (time_key)
);

-- ------------------------------------------------------------
-- ------------------------------- Load data into TIME  table -
-- ------------------------------------------------------------

LOAD DATA LOCAL INFILE '/Users/evs/Desktop/GroceriesDB/Time.csv' 
INTO TABLE Time 
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';


-- ------------------------------------------------------------
-- ------------------------ Modify TIME  table for assignment -
-- ------------------------------------------------------------

ALTER TABLE Time DROP COLUMN `date`;
ALTER TABLE Time DROP COLUMN `day_of_week`;
ALTER TABLE Time DROP COLUMN `day_number_in_month`;
ALTER TABLE Time DROP COLUMN `day_number_overall`;
ALTER TABLE Time DROP COLUMN `week_number_in_year`;
ALTER TABLE Time DROP COLUMN `quarter`;
ALTER TABLE Time DROP COLUMN `fiscal_period`;
ALTER TABLE Time DROP COLUMN `holiday_flag`;


-- ------------------------------------------------------------
-- ------------------------ Create Schema of SALES_FACT table -
-- ------------------------------------------------------------

DROP TABLE IF EXISTS `Sales_Fact`;

CREATE TABLE `Sales_Fact` (
    `time_key` DOUBLE,
    `product_key` DOUBLE,
    `promotion_key` DOUBLE,
    `store_key` DOUBLE,
    `dollar_sales` DOUBLE,
    `unit_sales` DOUBLE,
    `dollar_cost` DOUBLE,
    `customer_count` DOUBLE,
    FOREIGN KEY (time_key) REFERENCES Time (time_key)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (product_key) REFERENCES Product (product_key)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (store_key) REFERENCES Store (store_key)
        ON DELETE SET NULL
        ON UPDATE CASCADE
); 

-- ------------------------------------------------------------
-- -------------------------- Load data into SALES_FACT table -
-- ------------------------------------------------------------

LOAD DATA LOCAL INFILE '/Users/evs/Desktop/GroceriesDB/Sales_Fact.csv' 
INTO TABLE Sales_Fact
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';

-- ------------------------------------------------------------
-- ------------------- Modify SALES_FACT table for assignment -
-- ------------------------------------------------------------

ALTER TABLE `Sales_Fact` DROP COLUMN `promotion_key`;
ALTER TABLE `Sales_Fact` DROP COLUMN `unit_sales`;
ALTER TABLE `Sales_Fact` DROP COLUMN `dollar_cost`;
ALTER TABLE `Sales_Fact` DROP COLUMN `customer_count`;
