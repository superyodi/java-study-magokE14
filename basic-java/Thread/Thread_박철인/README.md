### 구성 클래스
- ChatServer
  - 포트를 열고 클라이언트들의 요청(바인딩, 메시지 등)을 처리하는 서버
- ChatServerProcessThread
  - 클라이언트당 하나의 쓰레드를 생성하여 통신을 해주는 쓰레드
- ChatClientApp
  - 유저에 대한 정보(대화명)을 들고 서버에게 연결을 요청하는 클라이언트앱
- ChatWindow
  - UI(awt)를 제공하는 클래스(서버의 브로드캐스트를 받는 쓰레드가 포함되어있다.)


### 서버와 클라이언트의 구조
![image](https://user-images.githubusercontent.com/43160639/175069175-b0fda5ca-bed1-43b9-8af0-38711a0106ee.png)

### 참고자료 및 소스
- https://victorydntmd.tistory.com/135 
