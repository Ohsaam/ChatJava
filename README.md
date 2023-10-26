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
---

# 이슈관리 & 회고

## 로그인 관련 이슈
![image](https://github.com/Ohsaam/ChatJava/assets/96507825/521165b4-ce33-46bc-9927-54c9922a8946)

- 설계 방향) 아이디/패스워드/대화명/성명 + 입력 후 등록 누르면 -> 해당 디비에 저장하여 로그인 버튼 눌렀을 때 그 값을 꺼내와서 "로그인이 성공되었습니다." 라고 설계했다.


- 회원가입 창에서 MemberShipView을 이용하여 값을 받아옴 -> 처음엔 무작정 클래스 생성자 안 this를 통해서 값을 getText() 하려고 했는데, 처음 설계 방향 자체를 잘못 잡았다. -> 그래서 테이블 스키마 자체를 다시 설계하여 디비와 연동했다. 그리고 해당 쿼리문을 다시 짜서 -> select 문으로 nickname을 찾고 -> setString 자체를 username/password로 진행했다. (findNickName 메소드 구현) 해서 회원가입 창에서 값을 읽어들이는 부분을 해결했다.  ( findNickName 메소드는 소켓통신에 key값으로 닉네임을 넘기는 디비 메소드)
    




## 회원가입 이슈 

### 패스워드 입력시 : 값들이 입력되는데 캡슐화로 ( *** ) 바꾼다.
- 처음엔 변수를 2개를 써서 -> 스트링으로 읽은 다음 -> replace해서 변환하려고 했다.(**) 하지만 이 부분은 이렇게 하는 것 보단 구글링을 통해 JPasswordField 이용하여 구현완료

### 아이디  / 패스워드 / 닉네임 디비연동



![image](https://github.com/Ohsaam/ChatJava/assets/96507825/3f9750aa-40a3-4690-ab0a-df5b91c740b8)



```java
java.sql.SQLSyntaxErrorException: ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
```
- 수정 했더니 해당 에러가 발생했고, 그렇다면 테이블 또는 뷰를 생성해서 연동시켜야했다. 처음엔 오라클 연동을 한 번 해봐서 자신은 없었지만 구선생을 통해 쿼리문을 짰다.


- 처음엔 단순히 인스턴스화를 통한 전역변수 이용을 하여 구현 하려고 했다. 처음엔 DB를 설계를 하지 않았는데, 아이디,패스워드, 회원정보를 저장 해야 된다는 필요성을 느껴 toad에서 테이블 등록(member) 후 아이디와 패스워드, 닉네임(회원가입창)에서 받아와서 Dao 클래스에 (Save) 메소드에서 해당 내용을 저장하는 디비를 완성했다.
(Save 메소드는 로그인을 하기 위한 회원내용 저장)


## 소켓통신 이슈

### 소켓통신 연동 이슈 
- 지금까지 연동하여 로그인을 했을 때 채팅창이 열리는 채팅프로그램을 구현 하고 싶었다.(요구사항)
  
- 하지만 지금까지 설계한 부분으로는 클래스 설계를 다시 해야 되는 상황이다. 우선 필자가 생각한 설계 방향은 우선 아이디와 비밀번호를 입력하면, 디비에 넘기는데 여기서 추가로 해야 될 부분은 닉네임을 추출하여 소켓과 연동시키는 설계방향으로 진행했다. 

- 이렇게 구현을 먼저하고 설계를 다시 하니 일을 두 번 하는 번거로움이 발생했다. 이 부분만 줄였어도 더 많은 기능을 구현 했을텐데 안타깝다. 다시 한 번 설계의 중요성을 느꼈고, 앞으론 Class Diagram을 이용해 설계를 먼저 해야겠다.
  
- 
### 소켓통신 구현 이슈
- 우선 싱글스레드가 아닌, 멀티스레드로 구현 했기 떄문에 회원 탈퇴 기능을 추가 했을 때, 2개의 채팅창이 있을 때, 한쪽에서 탈퇴하면 바로 Update 하는 메소드를 설계했다.

- 처음엔 Client/ClientThread만 수정하고 -> dtm.getValutAt() / dtm.setValueAt 이벤트 처리 메소드 설계 및 디비에서 삭제하는 메소드 설계 -> 이런 논리로 회원탈퇴를 눌렀을 때 해당 로그인한 아이디의 내용을 디비에서 삭제하고, 해당 row을 줄이는그리고 로그인 시 해당 회원정보는 삭제 되었기 때문에 다시 접속해야 되는 방향으로 설계했다. 하지만 시점의 문제가 발생했다.
  
- A라는 회원이 먼저 로그인하고 B라는 회원이 로그인 했을 때, 먼저 들어온 A를 회원 탈퇴허면 그 내용을 B에 반영하지 못했다. 이 문제를 겪게 되면서 클래스 시점에 대한 고찰을 다시 한 번 하게 된 것 같다. 클라이언트가 소켓을 서버에 전송하게 되면 (oos)서버에서 그 내용을 읽고(ois)  다시 전송하게 되는데 이 전송과정에 대한 구현이 빠져서 이슈가 발생한 것이였다.

- 이 부분을 해결하기 위해서 필자는 LogoutRequest,removeList,sendUserList 3개의 메소드를 구현했다.

 ```java 
 LogoutRequest

	public void LogoutRequest(String nickName) {
	    try {
	        String message = nickName + "님이 퇴장하였습니다.";
	        broadCasting(210 + "#" + nickName + "#" + message);
	        // 사용자 목록 업데이트
	        removeList(nickName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

// 사용자 목록에서 사용자 제거
	public void removeList(String nickName) {
	    for (SocketServerThread sst : ss.globalList) {
	        if (sst != null && sst.chatName.equals(nickName)) {
	            ss.globalList.remove(sst);
	            break;
	        }
	    }
	}

	
	// 사용자 목록을 클라이언트에게 전송
	//서버에 접속할 때 또는 사용자 목록을 요청할 때 사용
	public void sendUserList(SocketServerThread cthread) throws IOException {
	    StringBuilder userListMessage = new StringBuilder("100#"); // 목록 메시지를 만들기 위한 문자열 빌더
	    for (SocketServerThread sst : ss.globalList) {
	        userListMessage.append(sst.chatName).append("#");
	    }
	    /**
	     * 루프 내에서, 각 클라이언트 스레드 sst의 chatName을 가져와서 userListMessage에 # 문자와 함께 추가한다.
	     */
	    cthread.send(userListMessage.toString());
	    System.out.println("회원탈퇴");
	}
```

- 이 부분을 구현하면서 가장 어려웠던 부분은 globallist를 이용해야 된다는 건 인지하고 있었지만, 조건을 어떤식으로 걸어야 될지 한참 고민 한 것 같다. 해당 메소드를 구현하면서 이슈는 해결했고, 이를 기점으로 1차 프로젝트는 마무리했다.



### 포트연결 및 닉네임 연동 오류

![image](https://github.com/Ohsaam/ChatJava/assets/96507825/050fc5d7-9683-4575-9f45-2ecade1163ec)
![image](https://github.com/Ohsaam/ChatJava/assets/96507825/9e5783e9-5e9b-4515-b5e4-a3f858e0cca0)


- 소켓 연동은 성공 했으나 닉네임을 받지 못하는 해당 오류가 발생했다.

 ```java
java.util.NoSuchElementException
	at java.base/java.util.StringTokenizer.nextToken(StringTokenizer.java:347)
 ```
- server/ serverThead /clientThread 모두 닉네임을 연동 시켜야 된다고 판단했고 그 문제가 되는 클래스 내부 메소드 로그를 찍어서 문제 되는 부분을 확인했다. -> 
  
- 이 부분에서 처음 생각한 방향은 디비를 사용하지 않고 인스턴스화, 클래스(this)를 통한 방향으로 진행을 설계하여 세션의 개념처럼 nickname을 싱글톤으로 구현했다. 하지만 이 부분에서 필자가 간과한 부분이 있다. 소켓통신 프로그램은 멀티스레드인데 싱글톤으로 따로 만들었다니. 해당 부분을 진행하면서 스레드에 대한 공부를 다시 하게 됐다. [블로그 참고](https://velog.io/@qlwb7187/%EC%9E%90%EB%B0%94-%EC%8A%A4%EB%A0%88%EB%93%9C%EA%B3%B5%EB%B6%80)
  
## troubleshooting
- 해당 오류 관련된 부분은 [필자 블로그](https://velog.io/@qlwb7187/series/Java-%EC%98%A4%EB%A5%98)란에서 진행하고 있다. 
   
