create DATABASE IF NOT EXISTS emall;
use emall;
#setting
create table IF NOT EXISTS Member
(
    Id       INT          not null primary key auto_increment,
    Account  varchar(30)  not null unique key,
    Password varchar(128) not null
);

create table IF NOT EXISTS Product
(
    Id       int         not null primary key auto_increment,
    Name     varchar(30) not null,
    Price    int         not null, -- 售價
    Quantity int         not null  -- 庫存
);

create table IF NOT EXISTS `Order`
(
    OrderId   int not null primary key auto_increment,
    MemberId  int not null,
    Price     int not null,     -- 訂單金額
    PayStatus boolean default 0 -- 付款狀態
);

create table IF NOT EXISTS OrderDetail
(
    OrderItemSN int not null primary key auto_increment,
    OrderId     int not null,
    ProductId   int not null,
    Quantity    int not null, -- 數量
    StandPrice  int not null, -- 單價
    ItemPrice   int not null  -- 單品項總價
);

#insert init data
#member
insert into Member(account, password)
values ('458', '202cb962ac59075b964b07152d234b70'),
       ('55688', '202cb962ac59075b964b07152d234b70'),
       ('1713', '202cb962ac59075b964b07152d234b70');

#product
insert into Product(name, price, quantity)
values ('osii 舒壓按摩椅', 98000, 5),
       ('網友最愛起司蛋糕', 1200, 50),
       ('真愛密碼項鍊', 8500, 20),
       ('二手鍵盤',650,0);

#order
insert into `Order`(memberid, price, PayStatus)
    values ('1', 98000, 1),
           ('2', 9700, 0),
           ('3', 2400, 1);

#orderDetail
insert into OrderDetail(orderid, productid, quantity, standprice, itemprice)
    values (1,1,1,98000,98000),
           (2,2,1,1200,1200),
           (2,3,1,8500,8500),
           (3,2,2,1200,2400);


#Stored Procedure
        delimiter $
#used by orderDaoImpl
create procedure createOrder(
    in in_memberId int,
    in in_price int,
    out out_orderId int
)
begin
    insert into `Order`(memberId, price) values (in_memberId, in_price);
    set out_orderId = last_insert_id();
end $


create procedure createOrderItems(
    in in_orderId int,
    in in_productId int,
    in in_quantity int,
    in in_standPrice int,
    in in_itemPrice int
)
begin
    insert into OrderDetail(orderId, productId, quantity, standPrice, itemPrice)
    values (in_orderId, in_productId, in_quantity, in_standPrice, in_itemPrice);
end $

create procedure getOrderById(in in_orderId int)
begin
    select OrderId, MemberId, Price, PayStatus
    from `Order`
    where OrderId = in_orderId;
end $

create procedure getOrderDetailsByOrderId(in in_orderId int)
begin
    select od.orderitemsn, od.orderid, od.productid, p.Name, od.quantity, od.standprice, od.itemprice
    from OrderDetail od
             left join Product p
                       on od.ProductId = p.Id
    where OrderId = in_orderId;
end $

create procedure getOrdersByMember(in in_memberId int)
begin
    select OrderId, MemberId, Price, PayStatus
    from `Order`
    where MemberId = in_memberId
    order by OrderId desc;
end $

#used by orderDaoImpl
create procedure createMember(
    in in_account varchar(30),
    in in_password varchar(128),
    out out_memberId int
)
begin
    insert into Member(account, password)
    VALUES (in_account, in_password);
    SET out_memberId = LAST_INSERT_ID();
end $

create procedure getMemberByAccount(in in_account varchar(30))
begin
    select id, account, password from Member where account = in_account;
end $

create procedure getMemberById(in in_memberId int)
begin
    select id, account, password from Member where id = in_memberId;
end $

#used by product
create procedure getProductById(in in_id int)
begin
    select id, name, price, quantity from Product where id = in_id;
end $

create procedure getProducts()
begin
    select id, name, price, quantity from Product where quantity > 0 ;
end $

create procedure createProduct(
    in in_name varchar(30),
    in in_price int,
    in in_quantity int,
    out out_productId int
)
begin
    insert into Product(name, price, quantity)
    values (in_name, in_price, in_quantity);
    set out_productId = last_insert_id();
end $

create procedure updateQuantity(
    in in_productId int,
    in in_quantity int
)
begin
    update Product set Quantity = in_quantity where Id = in_productId;
end $




