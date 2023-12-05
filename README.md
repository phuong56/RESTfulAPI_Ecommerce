# RESTfulAPI_Ecommerce
# GIỚI THIỆU
- Đây là restful API về ecommerce được code bởi một mình tôi và tôi đã sử dụng spring boot để viết ra chương trình này
- Test bằng Post Man
  ########################################################################################################################################################################################################
  # CÁC CHỨC NĂNG TRONG RESTFUL API
  1. Tương tác với cở sở dữ liệu mysql bằng interface JpaRepository bằng cách sử dụng @Entity và @Table cho những model class
  2. Sử dụng JWT service để authentication user để test (/auth/me)
  3. Mã hóa mật khẩu
  4. Register sau lưu user vào data base với verifyEmail =false
  5. Login with verifyemail if(verifyMail==true) thì login được mà để login được phải vào stmp4dev để lấy token sau đó vào auth/verify để đưa token vào param để set biến verifyEmail =true
  6. Get all products qua localhost:(your port)/products
  7. Get all web order qua localhost:(your port)/order
  8. Có Validator cho các field của user
    - Not null and blank cho các field
    - Riêng email thì phải chuẩn form email (xxx@xxx)
    - Password phải có patterm ít nhất một chữa hoa, 1 số
    - Size của mỗi field là từ [3,100]
  Link thiết kế cơ sở dữ liệu https://dbdiagram.io/d/Ecommerce-diagram-db-656940303be149578717b96a
