# JAVA BASIC

- 클래스와 객체의 차이는 무엇인가?
    
    현실의 객체가 갖는 속성과 기능은 추상화되어 클래스에 정의된다.
    
    클래스는 구체화되어 프로그램의 객체가 된다.
    
    설계도(클래스)는 하나의 종규(Type, 형)이 되고 설계도를 통해 나온 제품이 객체가 됨.
    
    | 클래스 | 객체 |
    | --- | --- |
    | 객체를 정의 = 객체의 설계도 | 클래스를 구체화 |
    | 직접 사용 불가, 직접 사용되는 객체를 만들기 위한 틀 제공 | 실제로 동작 |
    | 데이터 타입 | 메모리에 생성된 데이터 |
    
- 객체와 인스턴스의 차이는 무엇인가?
    
    클래스의 타입으로 선언되었을 때 객체라고 부르고, 그 객체가 메모리에 할당되어 실제 사용될 때 인스턴스라고 부른다.
    
- Call by value와 Call by reference의 차이
    
    
    ![Untitled](JAVA%20BASIC%20b954a2a615dc4a02980f2235fc729500/Untitled.png)
    
    참조 변수의 경우 ‘주소값’을 변수에 저장하고 그 주소값은 힙 영역에 객체의 값들을 저장
    
    자바는 call by value를 사용한다!
    
    [https://velog.io/@ahnick/Java-Call-by-Value-Call-by-Reference](https://velog.io/@ahnick/Java-Call-by-Value-Call-by-Reference)
    
    ⇒ 근데 왜 string은 일반적인 참조 변수(인스턴스)처럼 작동하지 않을까?
    
- Primitive type 과 Reference type의 차이
    
    primitive type : boolean, byte, short, int, long, float, double, char
    
    reference type : 나머지 모든 객체 (java.lang.Object를 상속받는 모든 객체) enum, array 등
    
    | primitive | reference |
    | --- | --- |
    | boolean, byte, short, int, long, float, double, char | 나머지 모든 객체 (java.lang.Object를 상속받는 모든 객체) enum, array 등 |
    | 실제 값이 바로 저장됨. ⇒ 스택에 저장 | 참조변수에는 주소값을, 실제 값은 heap에 저장됨 |
    | null 존재x | null이 존재 |
    
- 오버로딩과 오버라이딩?
    
    오버로딩 : 인자의 자료형, 개수, 순서 셋 중 하나가 하나라도 다름
    
    리턴타입의 차이는 의미x. 
    
    오버라이딩 : 자바에서 상속된 메소드 + a
    
    인자의 자료형, 개수, 순서, 리턴타입 똑같다. but 내용물이 다르다.
    
    접근제한자가 부모보다 범위가 넓거나 같아야 한다.
    
    부모보다 더 큰 예외를 던질 수 없다.
    
- 오토 박싱 & 오토 언박싱의 차이
    
    출처 : [https://gyoogle.dev/blog/computer-language/Java/Auto Boxing & Unboxing.html](https://gyoogle.dev/blog/computer-language/Java/Auto%20Boxing%20&%20Unboxing.html)
    
    - 기본 타입 : `int, long, float, double, boolean` 등
    - Wrapper 클래스 : `Integer, Long, Float, Double, Boolean` 등
    
    박싱 : 기본 → Wrapper
    
    언박싱 : Wrapper → 기본
    
    ```java
    // 박싱
    int i = 10;
    Integer num = new Integer(i);
    
    // 언박싱
    Integer num = new Integer(10);
    int i = num.intValue();
    ```
    
    ```java
    // JDK 1.5부터 자동으로 변환 지원
    // 오토 박싱
    int i = 10;
    Integer num = i;
    
    // 오토 언박싱
    Integer num = new Integer(10);
    int i = num;
    ```
    
    Wrapper 클래스로 사용하는 경우 Collection이 사용가능해져 확장 기능 사용 가능해짐
    
    *오토 박싱을 반복문에서 계속 사용하게 된다면 성능상 좋지 않으니 지양해야한다.
    
    오토 박싱을 하는 것이 다른 타입으로 형변환을 하는 것이기 때문이다.
    
- StringBuilder와 StringBuffer의 차이
    
    ![Untitled](JAVA%20BASIC%20b954a2a615dc4a02980f2235fc729500/Untitled%201.png)
    
    stringbuffer - 동기화 가능, 스레드 안전하다, 웹프로젝트, 약간 느림
    
    stringbuilder - 비동기, 스레드에 안전 보장 x, 혼자서 쓸 때, 버퍼보다 더 빠름
    
- 추상 클래스와 인터페이스의 차이
    
    [https://dingue.tistory.com/4](https://dingue.tistory.com/4)
    
    [JAVA_day7](https://www.notion.so/JAVA_day7-80525f61695b4ed0aac5e248bba69b14) 
    
- String a = “apple”, String b = new String(”apple”) 차이
    
    ![Untitled](JAVA%20BASIC%20b954a2a615dc4a02980f2235fc729500/Untitled%202.png)
