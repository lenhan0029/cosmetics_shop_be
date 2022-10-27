IV. Category
1.	Create new category
Link
http://localhost:8080/category   (chạy localhost, chưa deploy)
method: Post
permission: admin
Request data (String/text)
Kem dưỡng da
Response data
{
    "message": "Thêm thành công",
    "status": 200,
    "data": {
        "id": 3,
        "name": "Kem dưỡng da"
    }
}

2.	Update category
Link
http://localhost:8080/category/{idbrand}   (chạy localhost, chưa deploy)
method: PUT
permission: admin
Request data (String/text)
Kem trộn
Response data
{
"message": "Thay đổi thành công",
"status": 200,
"data": {
"id": 3,
"name": "Kem trộn"
}
}


3.	Delete category
Link
http://localhost:8080/category/{idcategory}   (chạy localhost, chưa deploy)
method: delete
permission: admin
Request data 
none
Response data
{
"message": "Xóa thành công",
"status": 200,
"data": null
}


4.	Get list category

Link 
http://localhost:8080/category    (chạy localhost, chưa deploy)
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
            "name": "mặt nạ",
            "types": [
                {
                    "id": 4,
                    "name": "mặt nạ đất sét"
                },
                {
                    "id": 1,
                    "name": "mặt nạ mắt"
                },
                {
                    "id": 3,
                    "name": "mặt nạ giấy"
                },
                {
                    "id": 2,
                    "name": "mặt nạ môi"
                }
            ]
        },
        {
            "id": 2,
            "name": "son",
            "types": []
        },
        {
            "id": 3,
            "name": "Kem dưỡng da",
            "types": []
        }
    ]
}
