1. Get user information

link: http://localhost:8080/userInformation?accountId=1

response
{
    "message": "Lấy thông tin thành công",
    "status": 200,
    "data": {
        "firstName": "le",
        "lastName": "nhan",
        "email": "acnhan321@gmail.com",
        "address": "279 An Dương Vương",
        "phoneNumber": "0987123457",
        "gender": "nam",
        "birthday": "2001-05-16",
        "image": "hinh.png"
    }
}

2. PUT edit user information

link: http://localhost:8080/userInformation?accountId=1

request: 
{
        "firstName": "le",
        "lastName": "nhan",
        "email": "acnhan321@gmail.com",
        "address": "279 An Dương Vương",
        "phoneNumber": "0987123457",
        "gender": "nam",
        "birthday": "2001-05-16",
        "image": "hinh.png"
    }
response: 
{
    "message": "Sửa thông tin thành công",
    "status": 200,
    "data": {
        "firstName": "le",
        "lastName": "nhan",
        "email": "acnhan321@gmail.com",
        "address": "279 An Dương Vương",
        "phoneNumber": "0987123457",
        "gender": "nam",
        "birthday": "2001-05-16",
        "image": "hinh.png"
    }
}