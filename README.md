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
  ########################################################################################################################
  Link thiết kế cơ sở dữ liệu https://dbdiagram.io/d/Ecommerce-diagram-db-656940303be149578717b96a
#####################################################################################
1. lưu ý hãy dùng file query db.sql để insert data để dễ dàng có các infor address, product, weborder ... (trừ LocalUser vì cái này mình phải dùng post man để vào link đăng ký >.<)
###########################################################################################################################################################################################
III CÁC BƯỚC SỬ DỤNG RESTFUL API#
0. tải smtp4dev https://github.com/rnwood/smtp4dev/releases/download/3.2.0-ci20221023104/Rnwood.Smtp4dev.Desktop-win-x64-3.2.0-ci20221023104.zip
1. khởi động smtp4dv
2. chạy đoạn code trong IDE
3. vào postman truy cập vào http://localhost8080/auth/register với method là POST nhé để đăng ký tài khoản nhớ nhập raw body for registerBody ở dạng Json nhé
4. sử dụng postman truy cập vào http://localhost8080/auth/login với method là POST để đăng nhập nhớ nhập raw body for loginBody ở dạng Json nhé
5. vào smtp4dev lấy token=xxxxxxxxxx lấy cái xxxxxxxx trong mail nhé
6. sau đó qua http://localhost8080/auth/verify phương thức POST cho param  token =xxxxxxxxxxxx (xxxxxxxxxx là cái token mình lấy trong stmp4dev)
7. sau đó trở lại http://localhost8080/auth/login nhấn send 1 lần nữa bạn sẻ login được
8. sau đó bạn có thể http://localhost8080/auth/me và điền token vào Bearer Token sau đó bạn sẽ có thông tin user của bạn
