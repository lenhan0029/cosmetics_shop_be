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