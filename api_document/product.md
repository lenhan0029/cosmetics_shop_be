1. GET list product (permit all)
http://localhost:8080/product
param: name (String), brand (String), type (String), category (String), star (1 to 5), priceFrom, priceTo, page , sortType (
ASC or DESC), discount ( 1 or 0)

response
{
    "message": "thành công",
    "status": 200,
    "data": {
        "content": [
            {
                "id": 1,
                "name": "Sửa rửa mặt Cetaphil",
                "image": "cetaphilsrm.png",
                "price": 10000,
                "rate": 5.0,
                "discount": 10,
                "status": 1
            }
        ],
        "pageable": {
            "sort": {
                "unsorted": false,
                "sorted": true,
                "empty": false
            },
            "pageNumber": 0,
            "pageSize": 12,
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalElements": 1,
        "totalPages": 1,
        "sort": {
            "unsorted": false,
            "sorted": true,
            "empty": false
        },
        "numberOfElements": 1,
        "first": true,
        "size": 12,
        "number": 0,
        "empty": false
    }
}

2. GET product by id ( permit all)

http://localhost:8080/product/2
{
    "message": "Thành công",
    "status": 200,
    "data": {
        "id": 2,
        "name": "mask west",
        "image": "maskwest.png",
        "price": 10000,
        "rate": 10,
        "quantity": 10,
        "description": null
    }
}

3. Create product (admin)
POST
http://localhost:8080/product
request
{
    "name":"mask nature",
    "image":"maskhoney.png",
    "price":10000,
    "rate":5,
    "quantity":10,
    "description":"đẹp",
    "id_brand":1,
    "id_type":1,
    "id_promotion":""
}

response

{
    "message": "Thành công",
    "status": 200,
    "data": {
        "id": 4,
        "name": "mask nature",
        "image": "maskhoney.png",
        "price": 10000,
        "rate": 5,
        "quantity": 10,
        "description": "đẹp"
    }
}