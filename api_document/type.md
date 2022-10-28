1. Get type by category ( permit all)
http://localhost:8080/type?idCategory=1
{
    "message": " thành công",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "mặt nạ mắt"
        },
        {
            "id": 2,
            "name": "mặt nạ môi"
        },
        {
            "id": 3,
            "name": "mặt nạ giấy"
        },
        {
            "id": 4,
            "name": "mặt nạ đất sét"
        }
    ]
}

2. create type ( admin)
POST
http://localhost:8080/type
request
{
    "name": "mặt nạ than tre",
    "id_category": "1"
}
response
{
    "message": "tạo thành công",
    "status": 200,
    "data": {
        "id": 5,
        "name": "mặt nạ than tre"
    }
}

3. update type (admin)
PUT
http://localhost:8080/type/5
request
{
    "name": "mặt nạ than tre đen",
    "id_category": "1"
}
response
{
    "message": "thay đổi thành công",
    "status": 200,
    "data": {
        "id": 5,
        "name": "mặt nạ than tre đen"
    }
}

