# Spring Boot Application Task 1 (```Order Product Payment```)

This is the Task-1 project Spring Boot (version 2.7.5) application using Java (version 1.8)/ Maven / PostgreSQL.

## About

This application is based on the entities ```Order - Product - Payment``` . 

## About the Entity Relation
 
* Order to Product ```many-to-many```
* Payment to Order ```one-to-one```

Here are some endpoints you can call:

### Create an Order
```
POST http://localhost:8080/app/order/create
Content-Type: raw/json
{
    "name": "Dhruvi"
}
```
### Get an Order
```
GET http://localhost:8080/app/order/1
```
### Get all Orders
```
GET http://localhost:8080/app/order/all
```

### Create a Product
```
POST http://localhost:8080/app/product/create
Content-Type: raw/json
{
            "name": "Handbag",
            "price": 2000
}
```
### Get a Product
```
GET http://localhost:8080/app/product/1
```
### Get all Products
```
GET http://localhost:8080/app/product/all
```
### Delete a Product
```
DELETE http://localhost:8080/app/product/delete/5
```
### Update a Product

```
PUT http://localhost:8080/app/product/1
Content-Type: raw/json
{
    "name": "Hand-bag",
    "price": 3000
}
```

### Add an existing Product to Order
```
GET http://localhost:8080/app/order/1/product/1
```
### Remove a Product from Order
```
DELETE http://localhost:8080/app/order/1/product/1
```

### Get all the orders with payment done
```
GET http://localhost:8080/app/payment/getAll
```
### Create Payment for an order
```
GET http://localhost:8080/app/payment/create/1/100134
```


