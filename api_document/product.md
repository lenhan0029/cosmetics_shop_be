1. GET list product (permit all)
http://localhost:8080/product
{
    "message": "thành công",
    "status": 200,
    "data": {
        "content": [
            {
                "id": 2,
                "name": "mask west",
                "image": "maskwest.png",
                "price": 10000,
                "rate": 5
            },
            {
                "id": 3,
                "name": "mask honey",
                "image": "maskhoney.png",
                "price": 10000,
                "rate": 5
            },
            {
                "id": 1,
                "name": "Mặt nạ ",
                "image": "mask1.png",
                "price": 20000,
                "rate": 5
            }
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 12,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 3,
        "size": 12,
        "number": 0,
        "first": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "numberOfElements": 3,
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