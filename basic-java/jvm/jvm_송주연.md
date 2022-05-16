# Java 



## JDK, JRE, JVM



![Differences between JDK, JRE and JVM - GeeksforGeeks](https://media.geeksforgeeks.org/wp-content/uploads/20210218150010/JDK.png)

+ JVM
  + Java Virtual Machine
  
  + 자바 소스코드(.java)로부터 만들어지는 자바 바이너리 파일(.class)을 실행할 수 있다.
  
  + OS 별로 존재
  
    + 하지만 바이너리 파일은 어느 JVM에서도 실행 가능 
  
    
  
+ JRE

  + Java Runtime Environment
  + JVM + 자바프로그램 실행에 필요한 라이브러리 파일 등 
    + 클래스 로더, 자바 API 등등

+ JDK

  + Java Development Kit
  + JRE + 개발을 위한 도구
  + 컴파일러, 디버그 도구 등

    + 사용자가 자바를 다운받을때 JDK로 다운받음 





## JVM



심층적인 자바 공부에 앞서 JVM을 공부해야 하는 이유가 뭘까?

혹시 자바언어로 개발을 하면서 static 변수를 본 적이 있고, static 변수의 존재에 대해 의문을 가져본적이 있는가? 

그렇다면 static 변수에 대해 먼저 알아보자 



### Static 변수 

static은 클래스 변수를 선언할때 붙이는 표현이다. 때문에 클래스 변수를 static 변수라고 표현한다. 

그렇다면 클래스 변수는 뭘까?



#### Q. 클래스 변수와 인스턴스 변수의 차이는 무엇일까요?



![Java_ static변수, static메소드, 변수 유효범위, 싱글톤패턴, 배열](https://blog.kakaocdn.net/dn/vkdWX/btrinApSlRj/IunknXtL85BT3MCHKTTXjK/img.png)

*[참고 자료](https://codebyjihye.tistory.com/entry/1115-static%EB%B3%80%EC%88%98-static%EB%A9%94%EC%86%8C%EB%93%9C-%EB%B3%80%EC%88%98-%EC%9C%A0%ED%9A%A8%EB%B2%94%EC%9C%84-%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4-%EB%B0%B0%EC%97%B4)*



A. 

클래스 변수는 static으로 선언하고 클래스가 메모리에 올라갈 때 한번 만들어진 뒤 **모든 인스턴스가 같은 클래스 변수를 공유**한다. 하지만 인스턴스 변수는 클래스의 인스턴스들이 제각각의 인스턴스 변수들을 가진다. 

![img](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ed8717c0-89b0-4439-b984-51e0a315fe37/21226F42578D2F8137.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220411%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220411T154915Z&X-Amz-Expires=86400&X-Amz-Signature=087ecf7cc7b604e172eada3b3dc15e50bf8a33a0b4bfa5376f483f46f865dd54&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%2221226F42578D2F8137.jpg%22&x-id=GetObject)

클래스 변수는 모든 인스턴스가 공유하는 변수다. 모든 인스턴스에게 공유되는 변수를 만드려면 일반적인 인스턴스 변수와 클래스 변수는 생성되는 시점이 달라야 한다. 



클래스 변수와 인스턴스 변수가 생성되는 시점을 알아보기 전에 사용자가 작성한 자바 코드가 어떤 과정을 거쳐 실행되는지 알아볼 필요가 있다. 



### 자바 실행 과정

.java(자바 언어로 작성된 파일)을 자바 컴파일러가 .class(바이트 코드 파일)로 만든다. 그리고 .class 파일을 JVM이 읽고 실행한다. 자바 바이트 코드는 JVM이 설치된 어떤 운영체제에서도 실행 가능하다. 

 

#### 자바는 컴파일러 언어? 인터프리터 언어?



![110245609-5b1faa80-7fa7-11eb-95e0-af63706f94d2](https://user-images.githubusercontent.com/41244373/110245609-5b1faa80-7fa7-11eb-95e0-af63706f94d2.png)

+ 자바 컴파일러 
  + 자바 컴파일러가 프로그래머가 작성한 .java 파일을 .class 라는 바이트 코드로 변환
+ 자바 인터프리터 
  + JVM에서 바이트 코드를 각 운영체제에 맞는 언어로 변환해서 실행 





![img](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/14851144-449b-46db-98a3-a6904158c399/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220411%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220411T164303Z&X-Amz-Expires=86400&X-Amz-Signature=96aace9cc23909b93164061d658e59752db850de5ec7c44ab9f388fac96abc32&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)



### Class Loader 

class 파일을 JVM으로 load한다. load된 클래스들은 Runtime Data Area에 배치된다. 



### Runtime Data Area 

JVM이 프로그램을 수행하기 위해 운영체제로 부터 할당받는 메모리 영역

각각의 목적에 따라 5개의 영역으로 나뉨 

+ Method Area (= Class Area)
  + **모든 스레드가 공유**하는 데이터 공간
  + 어떤 메소드가 호출되려면 먼저 해당 메소드를 가지는 클래스 파일에 대한 정보가 필요한데 그 정보를 저장하고 있는 곳 
  + 클래스 로더가 클래스 파일을 읽어오면 클래스 정보를 파싱해서 메소드 영역에 저장 
    + 클래스 정보, 변수 정보, static 변수 정보, 상수 정보 등
    + **클래스 변수도 이 영역에 생성**
  + GC 실행안됨 
+ Heap Area
  + **모든 스레드가 공유**하는 데이터 공간
  + `new` 명령어로 생성된 인스턴스 객체가 저장되는 구역
  + 공간이 부족해지면 GC 실행됨 
+ Stack Area
  + 각 스레드마다 하나씩 생성 
  + 메소드 안에서 사용되는 값들이 저장됨 
    + 매개변수(파라미터), 지역변수 등
  + 왜 stack으로 구현됐는지 생각해보기 
+ PC register
+ Navtive Method Stack 



> 참고
>
> + [피엔귄의 잡다한 코딩_[JAVA] 자바 프로그램 실행 과정 및 기본 구조](https://pienguin.tistory.com/entry/JAVA-%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8-%EC%8B%A4%ED%96%89-%EA%B3%BC%EC%A0%95-%EB%B0%8F-%EA%B8%B0%EB%B3%B8-%EA%B5%AC%EC%A1%B0)
> + https://blog.wanzargen.me/16
> + https://www.holaxprogramming.com/2013/07/16/java-jvm-runtime-data-area/
