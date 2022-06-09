`String str1 = new String("abc")` 는 new 연산자를 이용한 생성법이고,

`String str2 = "abc";` 는 문자열 리터럴 생성법이다.

new 연산자는 메모리의 `heap` 영역에 할당되고

리터럴 방식은 `String Constant Pool` 영역에 할당된다.

![image](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/97163d6d-c99f-48b8-a229-fb50a6e03ce1/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220424%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220424T124730Z&X-Amz-Expires=86400&X-Amz-Signature=40e064de0222f235b14c0e408b37a441b53d638804a6987ad2ef2ec8036198c8&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

String Constant Pool 영역은 Java Heap Memory 내에 문자열 리터럴을 저장한 공간이며 HashMap으로 구현되어 있다. 한 번 생성된 문자열 리터럴은 변경될 수 없으며 리터럴로 문자열을 생성하면 내부적으로 `String.intern()` 이 호출된다.

String Pool에 같은 값이 있는지 찾고 있다면 그 참조값을 반환, 없다면 pool에 문자열을 등록 후 해당 참조값이 반환되는 형태이다.

```java
String str = "bepoz";
String str2 = "bepoz";
String str3 = **new** String("bepoz");
System.out.println(str==str2); *//true*    
System.out.println(str==str3); *//false*
String str = "bepoz";
String str2 = "bepoz";
String str3 = **new** String("bepoz").intern();
System.out.println(str==str2); *//true* 
System.out.println(str==str3); *//true*
```

[Immutable Object(불변 객체) 에 대해](https://github.com/Be-poz/TIL/blob/master/Java/Immutable Object (불변 객체) 에 대해.md)