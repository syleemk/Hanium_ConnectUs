<<<<<<< HEAD
# ConnectUS
**한이음 프로젝트-소상공인 전용 온라인 판매+펀딩 플랫폼🙌**
1. 쇼핑몰 기능
- 소상공인들이 온라인에서 물품을 등록하고 판매할 수 있음
2. 크라우드 도네이션 기능
- 재난 발생 시 특정 지역에 필요한 물품을 소상공인에게서 얻고 소비자가 크라우드 펀딩 형식으로 가격을 지불한 후 재난 지역에 배송

* * *

### 기획 의도
   - 비대면 소비 확산 -> 소상공인 전용 온라인 판매 플랫폼의 필요성을 느낌
   
   - 소상공인 물품 판매율을 높이기 위해 재난지역에 소상공인의 물품 사용
   
   - 소비자는 크라우드 도네이션으로 편리하게 기부를 할 수 있음
   
   - 투명한 기부 문화 확산

### 개발도구
Spring Boot, JPA, React.js, Docker, MariaDB

### 구현 화면
by 정은, 인환   

### API
by 한비(Admin, User), 수영(Product, Cart, Order), 현준(Funding)   
![image](https://user-images.githubusercontent.com/36736904/96230356-652e1600-0fd2-11eb-963f-75c46b9cd321.png)

### DB
![image](https://user-images.githubusercontent.com/36736904/96233286-2cdb0780-0fd3-11eb-9224-78fc79d0ba5a.png)



=======
# USER
- [x] POST v1/auth/users
- [x] POST v1/auth/login
- [ ] GET v1/auth/logout
- [x] GET v1/users/me
- [x] PUT v1/users/me
- [x] PUT v1/users/me/password
- [x] GET v1/email/userVerification
- [x] POST v1/email/password

# ADMIN
- [x] GET v1/admin/users
- [x] POST v1/admin/users
- [x] POST v1/admin/users/delete

# TODO
OAuth2 API <br/>
Redis

test test
>>>>>>> feature/v2/user
