# equals를 재정의 했다면 hashCode도 재정의해라.

## 논리적동치지만 주소값이 다른 객체
```java
    public static void main(String[] args)
    {
        Main1 obj1 = new Main1(1,2);
        Main1 obj2 = new Main1(1,2);

        HashMap<Main1, Integer> hm = new HashMap<>();

        hm.put(obj1, 1);
        hm.put(obj2, 2);

        System.out.println(hm.get(obj2)); //2
        System.out.println(hm.get(obj1)); //1
        
        System.out.println(hm.get(new Main1(1,2))); //null
    }
```

- 논리적동치도 체크를 해주어야 한다.
    - "equals" 재정의
    - 재정의 해주지 않으면, 클래스의 인스턴스는 논리적동치를 체크해주지 않는다.

```java
/* Object 클래스에 정의되어 있는 equals */
public boolean equals(Object obj) {
        return (this == obj);
}
```

```java
class Main1
{
    int a,b;

    Main1(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Main1))
            return false;
        Main1 main1 = (Main1)obj;
        return a == main1.a &&
            Objects.equals(b, main1.b);
    }
}
```
![캡처1](https://user-images.githubusercontent.com/43160639/197916701-51c4d29f-3b69-4c1b-b577-054e0b7cfe6a.PNG)


## 언제 equals()를 재정의 해줘야 되는가? 

- 클래스의 논리적 동치성을 체크하고자 할때(구현이 안되어있을 때)
- 박싱클래스를 비교해줄 때(Integer, String 등)
```java
@Test
    public void equals2(){
        String a = "abc";
        String b = new String("abc"); // 상수풀에 없는 다른 주소를 가짐

        assertEquals(a,b);
    }
```
- 두 객체가 같은 인스턴스 인지를 보는게 아니라, 같은 값을 갖는지(논리적동치) 확인하고자 할 때.
- 'equals'를 재정의 함으로써, 논리적동치 여부를 확인하고 그 클래스는 Map의 key와 Set의 원소로 활용될 수 있다.


## equals()의 규약
- 반사성 : x.equals(x)는 true
- 대칭성 : x.equals(y)가 true면 y.equals(x)도 true다.
- 추이성 : x.equals(y)가 true이고 y.equals(z)도 true이면, x.equals(z)는 true이다.
- 일관성 : x.equals(y)가 true면, 다시 호출되도 true이다.(x와 y가 변경되지 않는 조건에서.)
- Non-null : x.equals(null)은 false이다.

` equals()의 규약을 어기면 그 객체를 사용하는 다른 객체들이 어떻게 반응할지 알 수 없다. `

## 리스코프 치환 원칙
- https://github.com/superyodi/java-study-magokE14/blob/master/basic-java/SOLID/SOLID_%EB%B0%95%EC%B2%A0%EC%9D%B8.md
## 양질의 equals() 구현 방법
1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인.
2. instanceof 연산자로 입력이 올바른 타입인지 확인하다.
3. 입력을 올바른 타입으로 형변환한다.(2번에서 instanceof 검사를 했으니 100% 성공한다.)
4. 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.
5. equals를 재정의할 땐 hashCode도 반드시 재정의한다.

꼭 필요한 경우가 아니면 equals를 재정의하지 않는다. 대부분의 경우 Object의 equals 메서드가 원하는 비교를 정확히 수행한다.

## hashCode() 메서드 규약
- equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 객체의 hashCode 메서드는 항상 같은 해쉬값을 반환한다.(단, 애플리케이션을 재실행할 경우 달라질 수 있다.)
- obj1.equals(obj2)가 true면, 두 객체의 hashCode도 같다.
- 해쉬충돌로 인해 두 객체가 false여도 hashCode가 같은 경우가 있다.

## hashCode()를 재정의 하지 않았을 경우 생기는 문제점
- 같은 값을 가진 객체가 서로 다른 hash값을 갖게 될 수 있다.(해시충돌)
- 특히 HashMap의 key값으로 해당 객체를 사용할 경우 문제가 발생.

```java
@Test
    public void equals3(){
        Map<Main1, Integer> hm = new HashMap<>();
        hm.put(new Main1(1,2),1);

        Main1 obj = new Main1(1,2);
        
        int isOne = hm.get(obj); //NullPointerException!

        assertEquals(isOne, 1);
    }
```

- 이유?
    - Object의 필드값은 같더라도 해쉬값이 다르기 때문
- 해결책?
    - Object(Main1 클래스)의 hashCode()를 재정의 해주어야 한다.

```java
class Main1
{
    int a,b;

    Main1(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (!(obj instanceof Main1))
            return false;
        Main1 main1 = (Main1)obj;
        return a == main1.a &&
            Objects.equals(b, main1.b);
    }

    @Override
    public int hashCode(){
        return Objects.hash(b,a); //효율적인 hashCode는 아니지만 테스트는 통과한다.
    }
}
```

- 원리
    - 같은 버킷 안에(같은 해쉬값을 갖는) 다른 객체가 이미 있는 경우 `equals()`가 사용된다.
    - 즉, 해쉬충돌이 발생했어도 논리적동치인 객체라면 같은 객체로 본다.
    - equals()와 hashcode()를 같이 재정의 해야 하는 이유이다.
        - hashCode()를 재정의 하지 않는다면 같은 객체라도 다른 해쉬값을 가져 원하는 버킷을 찾을 수 없다.
        - equals()를 재정의 하지 않는다면 같은 버킷안에(같은 해쉬값에) 같은 객체 인지 확인 할수 없기 때문에 null을 리턴한다.

## 결론
- equals()와 hashCode()는 항상 같이 재정의 한다.
- 논리적동치여부를 위해서는 equals()와 hashCode()를 재정의하자.

## 참고자료
- https://velog.io/@sonypark/Java-equals-hascode-%EB%A9%94%EC%84%9C%EB%93%9C%EB%8A%94-%EC%96%B8%EC%A0%9C-%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%B4%EC%95%BC-%ED%95%A0%EA%B9%8C
