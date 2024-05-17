CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_sellcattle`(
	i_bovine_id int,
    i_sell_date date,
    i_buyer_id int,
    i_total_price double(10,2),
    i_vat_percentage double(5,2)
)
BEGIN
    DECLARE v_vat_value double(10,2);

	START TRANSACTION;
    
	INSERT INTO f100(movement_date, movement_credit) 
    VALUES (i_sell_date, i_total_price);
    
    SET @new_movement_id := last_insert_id(); 
    SET v_vat_value = i_total_price - (i_total_price/(1+(i_vat_percentage/100)));
    
    INSERT INTO f101(movement_id, vat_percentage, vat_value_sale)
    VALUES (@new_movement_id, i_vat_percentage, v_vat_value);
    
    INSERT INTO b102(bovine_id, sell_date, buyer_id, total_price, vat_percentage, movement_id)
    VALUES (i_bovine_id, i_sell_date, i_buyer_id, i_total_price, i_vat_percentage, @new_movement_id);
    
    UPDATE b100
    SET bovine_status = "SOLD", last_known_owner_id = i_buyer_id
    WHERE bovine_id = i_bovine_id;
    
    COMMIT;
END