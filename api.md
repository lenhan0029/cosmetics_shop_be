I. Đăng ký
1.	Link
http://localhost:8080/auth/signup  (chạy localhost, chưa deploy)
method: Post
permission: all
2.	Request data
{
    "userName": "lenhan",
    "password": "Test123@",
    "status": "1",
    "roleId": "1",
    "firstName": "nhaan",
    "lastName": "lee",
    "address": "hong co",
    "phoneNumber": "0987123457",
    "email": "lenhan112@gmail.com"
}
3.	Response data
{
    "message": "Signup successfull",
    "status": 200,
    "data": null
}
-----------------------------------------------------------------------------
II. Đăng nhập
1.	Link
http://localhost:8080/auth/login (chạy localhost, chưa deploy)
method: Post
permission: all
2.	Request data
{
    "username": "lenhan",
    "password": "Test123@"
}
3.	Response data
{
    "message": "login success",
    "status": 200,
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsZW5oYW4iLCJpYXQiOjE2NjYwMjEzMzMsImV4cCI6MTY2NjEwNzczM30.fSMWMo1jh2SuSEUnrW95bUHRnvM3gQKryllmQ6LM1irKKhvhc4sGgr2NBgxXwlRlavYXQqjRifCPkc6KZLcLyA",
        "type": "Bearer",
        "id": 1,
        "username": "lenhan",
        "roles": "admin",
        "status": true
    }
}
-----------------------------------------------------------------------------
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

-----------------------------------------------------------------
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
            "name": "Mặt nạ"
        },
        {
            "id": 2,
            "name": "Son"
        }
    ]
}



