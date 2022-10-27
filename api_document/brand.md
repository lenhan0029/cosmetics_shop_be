III. Brand
1.	Create new brand
Link
http://localhost:8080/brand  (chạy localhost, chưa deploy)
method: Post
permission: admin
Request data (String/text)
Hazelin
Response data
{
    "message": "Thêm thành công",
    "status": 200,
    "data": {
        "id": 2,
        "name": "Hazelin"
    }
}

2.	Update brand
Link
http://localhost:8080/brand/{idbrand}   (chạy localhost, chưa deploy)
method: PUT
permission: admin
Request data (String/text)
3CE
Response data
{
"message": "Thay đổi thành công",
"status": 200,
"data": {
"id": 3,
"name": "3CE"
}
}


3.	Delete brand
Link
http://localhost:8080/brand/{idbrand}   (chạy localhost, chưa deploy)
method: delete
permission: admin
Request data 
none
Response data
{
"message": "Thêm thành công",
"status": 200,
"data": null
}


4.	Get list brand

Link
http://localhost:8080/brand   (chạy localhost, chưa deploy)
method: GET
permission: all
Request data 
none
Response data
{
    "message": "thành công",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "sunplay"
        },
        {
            "id": 2,
            "name": "Hazelin"
        }
    ]
}
