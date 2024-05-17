CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buycattle`(
	i_bovine_prefix varchar(5),
	i_bovine_code int,
    i_breed varchar(50),
	i_color varchar(50),
    i_gender varchar(20),
    i_birth_date date,
    i_bovine_name varchar(100),
    i_bovine_status varchar(20),
    i_mothers_code int,
    i_fathers_code int,
    i_self_id int,
    i_buy_date date,
    i_seller_id int,
    i_total_price double(10,2),
    i_vat_percentage double(5,2)
)
BEGIN
	DECLARE v_new_id int;
    DECLARE v_vat_value double(10,2);
    
    START TRANSACTION;
    
    INSERT INTO f100(movement_date, movement_debit) 
    VALUES (i_buy_date, i_total_price);
    
    SET @new_movement_id := last_insert_id(); 
    SET v_vat_value = i_total_price - (i_total_price/(1+(i_vat_percentage/100)));
    
    INSERT INTO f101(movement_id, vat_percentage, vat_value_purchase)
    VALUES (@new_movement_id, i_vat_percentage, v_vat_value);
    
	INSERT INTO b100(bovine_prefix, bovine_code, breed, color, gender, birth_date, bovine_name, bovine_status, mothers_code, fathers_code, last_known_owner_id)
    VALUES (i_bovine_prefix, i_bovine_code, i_breed, i_color, i_gender, i_birth_date, i_bovine_name, i_bovine_status, i_mothers_code, i_fathers_code, i_self_id );
   
    SET @new_bovine_id := last_insert_id();
    
    INSERT INTO b101(bovine_id, buy_date, seller_id, total_price, vat_percentage, movement_id)
    VALUES (@new_bovine_id , i_buy_date, i_seller_id, i_total_price, i_vat_percentage, @new_movement_id);

	COMMIT;
END