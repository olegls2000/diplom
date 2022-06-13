insert into customer(firstName, lastName, registrationCode, email, telephone)
values ('Angelina', 'Mirami', 45315821, 'mirami.a@mail.pl', 48428810128);
insert into customer(firstName, lastName, registrationCode, email, telephone)
values ('Bill', 'Morrison', 53486114, 'morrison@gmail.com', 37253532020);
insert into customer(firstName, lastName, registrationCode, email, telephone)
values ('Roger', 'Smith', 9431574, 'r.smith@homtmail.com', 1862855245);

insert into product(name, skuCode, unitPrice)
values ('IPhone13Pro', 762456, 1300);
insert into product(name, skuCode, unitPrice)
values ('MacBook', 178245, 1800);
insert into product(name, skuCode, unitPrice)
values ('GalaxyS22', 524562, 750);
insert into product(name, skuCode, unitPrice)
values ('GalaxyTab', 554858, 900);
insert into product(name, skuCode, unitPrice)
values ('IntelI9', 453824, 600);
insert into product(name, skuCode, unitPrice)
values ('IntelI7', 752453, 500);
insert into product(name, skuCode, unitPrice)
values ('IntelI5', 214652, 400);
insert into product(name, skuCode, unitPrice)
values ('GalaxyWatch', 752155, 200);
insert into product(name, skuCode, unitPrice)
values ('IWatch', 325547, 300);
insert into product(name, skuCode, unitPrice)
values ('RedmiNote9', 1589247, 350);
insert into product(name, skuCode, unitPrice)
values ('GalaxyA7', 862168, 250);
insert into product(name, skuCode, unitPrice)
values ('Jbl310BT', 762951, 50);
insert into product(name, skuCode, unitPrice)
values ('IPad9', 615842, 450);
insert into product(name, skuCode, unitPrice)
values ('Xiaomi12', 524661, 800);
insert into product(name, skuCode, unitPrice)
values ('AirPods', 456258, 150);
insert into product(name, skuCode, unitPrice)
values ('GalaxyBuds', 125865, 145);
insert into product(name, skuCode, unitPrice)
values ('LenovoTab', 953158, 600);
insert into product(name, skuCode, unitPrice)
values ('YogaTab11', 621489, 359);
insert into product(name, skuCode, unitPrice)
values ('Xiaomi12Pro', 752154, 329);
insert into product(name, skuCode, unitPrice)
values ('IWatch7', 752156, 500);

insert into customer_order (order_number, customer_id, submission_date)
values ('a-1',
        (select id from customer where registrationCode = 45315821),
        current_timestamp);
insert into customer_order (order_number, customer_id, submission_date)
values ('a-2',
        (select id from customer where registrationCode = 53486114),
        current_timestamp);
insert into customer_order (order_number, customer_id, submission_date)
values ('a-3',
        (select id from customer where registrationCode = 9431574),
        current_timestamp);

insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'IntelI9'), 100,
        (select id from customer_order where order_number = 'a-1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'MacBook'), 50,
        (select id from customer_order where order_number = 'a-1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'Xiaomi12'), 400,
        (select id from customer_order where order_number = 'a-2'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'IPad9'), 70,
        (select id from customer_order where order_number = 'a-2'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'GalaxyA7'), 700,
        (select id from customer_order where order_number = 'a-1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'Jbl310BT'), 150,
        (select id from customer_order where order_number = 'a-1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'GalaxyS22'), 300,
        (select id from customer_order where order_number = 'a-3'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'IWatch7'), 120,
        (select id from customer_order where order_number = 'a-3'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'AirPods'), 550,
        (select id from customer_order where order_number = 'a-3'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'LenovoTab'), 250,
        (select id from customer_order where order_number = 'a-3'));