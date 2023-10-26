# ChatJava
> **개발기간 : 2023.10.20 ~ 2023.10.26**

<div align="center">
<img src="https://github.com/Ohsaam/ChatJava/assets/96507825/f166599f-5265-4a3a-a967-9990b27fd378" width="500">

<a href="https://hits.seeyoufarm.com"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FOhsaam%2FChatJava&count_bg=%23697361&title_bg=%23555555&icon=&icon_color=%23816C6C&title=visit&edge_flat=false"/></a>

---

</div>


## 프로젝트 소개 : 
- #### ChatJava는 소켓프로그래밍, 디비를 활용한 프로그램입니다. 

## 화면 구성 📺

### 회원가입

| 로그인  |  회원가입   | 
| :-------------------------------------------: | :-------------------------------------------: |
|  <img width="200" src="https://github.com/Ohsaam/ChatJava/assets/96507825/4a1b59fb-712a-4855-a474-36376593fc52"/> |    <img width="400" src="https://github.com/Ohsaam/Java/assets/96507825/7cd930ae-395b-45d4-b669-ab1989419089">
| 회원가입- 우편번호주소  |  회원가입- 회원가입완료 |  
| <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/acb04dc8-033d-4b89-aadc-4de163aaa951">  |  <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/8cd1ff4d-d5f1-409c-9302-850287d272b6">     |
| 회원가입- 필드검사  |  회원가입- (패스워드/아이디) 확인  |  
|  <img width="250" src="https://github.com/Ohsaam/ChatJava/assets/96507825/0175325d-db15-45a7-9321-d61c130f0744">                | <img width="250" src="https://github.com/Ohsaam/ChatJava/assets/96507825/24fb3585-e946-4949-94b0-b20c53de9d48">   |

### 채팅

| 채팅페이지  |  전송기능  |
| :-------------------------------------------: | :------------: |
|  <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/c8a5c7b3-42d1-4fdf-9a8a-d85f5149a8f8"/> |  <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/97420a62-d7dd-4d02-bfbf-55e3ed039e69"/>|  
| 회원탈퇴   |  닉네임 변경   |  
| <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/dafd4412-f3e8-4da9-b892-fc8c925879c9"/>   |  <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/73df6b5b-53f4-4e94-9b25-7c16eae17d0b"/>     |
| 닉네임변경2   |  글자색   |  
| <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/099e34b6-ee78-4eea-991e-7dfb53e39c22"/>   |  <img width="400" src="https://github.com/Ohsaam/ChatJava/assets/96507825/e1604621-2726-4077-aba7-1a9bbc95bcbc"/>     |


### 프로토콜 설계 
|  프로토콜 |  전송기능  |
| :-------------------------------------------: | :------------: |
|   Login     |  100        |
|   MESSAGE     |  200        |
|    CHANGE    |  202       |
|    DELETE    |  210       |
---

## 주요 기능 



### ⭐️ 로그인
- 회원가입, 로그인 2개의 기능 구현
- 회원가입 시 디비에 저장 되어 있는 ID/Paswword 가져와서 정보가 맞으면 로그인 성공, 틀리면 로그인 실패

### ⭐️ 회원가입
- 모든 필드을 충족 후 회원가입 가능 구현(유효성 검사)
- 회원가입 시 ID/Password/Nickname 디비 저장.

### ⭐️ 채팅창
- 전송/Enter 누르면 사용자에게 메세지 전송 기능 제공
- 닉네임변경 기능을 이용하여 해당 사용자 닉네임 변경 기능 제공
- 글자색 버튼 기능으로 채팅창 내 글자색 변경 가능
- 회원 탈퇴을 이용하여 저장 되어 있는 회원정보 삭제 기능 제공
- 추후 회원 리스트 CRUD 추가 및 업로드 예정 

## 시작 가이드

### Requirements

- [ojdbc6.jar](https://mvnrepository.com/artifact/oracle/ojdbc6/11.2.0.3)
- [JavaSE-17(zulu-17)](https://www.azul.com/downloads/#zulu)
### Installation
``` bash
$ git clone https://github.com/Ohsaam/ChatJava.git
$ cd ChatJava
```



## Stacks 🐈

### Environment

- <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=Eclipse&logoColor=white">  
- <img src="https://img.shields.io/badge/Toad-FFA900?style=for-the-badge&logo=Toad&logoColor=white">  
- ![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-007ACC?style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white)
- ![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)
- ![Github](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)             


### Development
- <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">
- <img src="https://img.shields.io/badge/Java-782A90?style=for-the-badge&logo=java&logoColor=white">


## 아키텍쳐
``` 
📦com
 ┣ 📂database
 ┃ ┣ 📂zipcodeview
 ┃ ┃ ┗ 📜DBConnectionMgr.java
 ┃ ┣ 📜DBConnection.java
 ┃ ┣ 📜MemberDao.java
 ┃ ┗ 📜MemberDTO.java
 ┣ 📂soket
 ┃ ┣ 📂client
 ┃ ┃ ┣ 📜SocketClient.java
 ┃ ┃ ┗ 📜SocketClientThread.java
 ┃ ┗ 📂server
 ┃ ┃ ┣ 📜Protocol.java
 ┃ ┃ ┣ 📜SocketServer.java
 ┃ ┃ ┗ 📜SocketServerThread.java
 ┗ 📂ui
 ┃ ┣ 📜LoginForm.java
 ┃ ┣ 📜MemberListFrame.java
 ┃ ┣ 📜MemberShipView.java
 ┃ ┣ 📜Sample.java
 ┃ ┗ 📜ZipCodeView.java
```
