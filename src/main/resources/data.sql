insert into menu_category (id, created_date, last_modified_date, available, name, finish, start)
    values (1, null, null, true, 'Coffee', null, null);

insert into item (id, created_date, last_modified_date, available, material, item_name, price, serve_time, category_id)
    values (1, null, null, true, 'Coffee and Milk','Macchiato', 0, '00:20', 1);

insert into coffee_shop_table (id, created_date, last_modified_date, empty, reserved)
    values (1, null, null, true, false);