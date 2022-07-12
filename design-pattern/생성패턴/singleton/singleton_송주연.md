# 싱글톤 패턴과 싱글톤 패턴의 trade off 

### 싱글톤 패턴 

프로그램에서 오직 한개의 인스턴스만 가지며, 이에 대해 전역적인 접근을 하고자 할때 

<img src="https://tech-people.github.io/images/design-pattern/singleton-pattern.png" alt="singleton-pattern" style="zoom: 67%;" />




### 사용을 고려해볼 상황

+ 프로그램 실행중 객체가 하나여야 하는 경우
+ 해당 객체에 전역적으로 접근 가능해야 하는 경우 
+ 생성시점을 조절하고자 하는 경우 



### 구현방식 

#### 구현1) 클래식

```java
public class Singleton {

	private static Singleton instance;
  
  private Singleton() {}
    
  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;    
  }
}
```



#### 병렬처리환경에서는 어떨까?

  클래식한 구현방법을 멀티 스레드 환경에서 사용했을때 2개 이상의 인스턴스가 생성되는 문제상황이 발생할 수 있다. 

스레드 2개가 동시에 해당 코드를 처리할 경우 각각의 스레드에서 instance를 null이라고 판단해 2개의 Singleton class 인스턴스를 생성할 수 있다. 싱글톤이 아니게 되는 것이다. 



#### 구현2) Eager Initialization

   이런 문제는 클래스 내부에 static 전역변수를 미리 생성해 놓으면 해결 가능하다. 

   static 변수로 인스턴스를 생성해놓았기 때문에 Class loading 단계에서 인스턴스가 생성되어 원자성을 보장한다. 

   ```java
   public class Singleton {
   
   	private static Singleton instance = new Singleton();
     
     private Singleton() {}
       
     public static Singleton getInstance() {
       return instance;    
     }
   }
   ```

   

   하지만 이런 방식은 인스턴스의 생성시점을 조절할 수 없다는 문제점이 있다. 

   해당 인스턴스가 사용되기 이전에 이미 메모리에 올라가있기 때문에 메모리를 효율적으로 사용할 수 없게 된다. 

   그렇다면 Thread-Safe을 보장하면서 생성시점을 조절할 수 있는 구현방식은 없을까?

   



#### 구현3) Thread Safe Singleton

아래 구현방식으로 Thread-Safe 하면서 인스턴스의 생성시점을 조절할 수있다. 

구현1)에서 getInstance() 함수를 `synchronized`를 사용해서 lock을 걸면 Thread-Safe한 결과를 얻을 수 있다. 



   ```java
   public class Singleton {
     private static Singleton instance;
     private Singleton() {}
     
     public static synchronized Singleton getInstance() {
       if (instance == null) {
         instance = new Singleton();
       }
       return instace;
     }
   }
   ```

하지만  `synchronized` 키워드는 오버헤드가 크다. 

이미 다른 스레드가 점유하고 있어 block된 자원을 대기하는 시간과 자원을 unlock/lock 하는 시간을 생각하면 퍼포먼스가 크게 저하될 수 있다. 

그렇다면 static 변수처럼 클래스 로딩 과정에서 인스턴스의 원자성을 보장하되, 인스턴스의 생성시점을 조절할 수 있는 구현방식은 없을까? 



#### 구현4) Bill Pugh Singleton

Bill Pugh가 고안한 방식으로 class 내부에 inner static helper/holder class 를 사용하는 방식으로 현재 가장 널리 쓰인다. 



```java
public class Singleton {
  private Singleton() {}
  
  private static class SingletonHelper {
    private static final Singleton INSTANCE = new Singleton();
  }
  
  public static Singleton getInstance() {
    return SingletonHelper.INSTANCE;
  }
}
```



SingletoneHelper class는 클래스가 로딩될때는 생성되지 않고 Singleton.getInstance() 가 실행될때 로딩된다. 

즉, 사용자가 getInstane()를 호출할때 생성되므로 생성시점을 조절할 수 있고, static 전역 변수로 생성되어 있으니 원자성도 보장된다. 









> 참고 자료
>
> + [철인님이 정리한 singleton 노트](https://github.com/superyodi/java-study-magokE14/blob/master/design-pattern/%EC%83%9D%EC%84%B1%ED%8C%A8%ED%84%B4/singleton/singleton_%EB%B0%95%EC%B2%A0%EC%9D%B8.md) 
> + [[생성 패턴] 싱글톤(Singleton) 패턴을 구현하는 6가지 방법](https://readystory.tistory.com/116)

