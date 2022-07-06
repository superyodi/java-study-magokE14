# Singleton

> 애플리케이션이 시작될 때, 어떤 클래스가 최초 한 번만 메모리를 할당(static)하고 해당 메모리에 인스턴스를 만들어 사용하는 패턴
> 

즉, 싱글톤 패턴은 '하나'의 인스턴스만 생성하여 사용하는 디자인 패턴.

(java에서는 생성자를 private으로 선언해 다른 곳에서 생성하지 못하도록 만들고, getInstance() 메소드를 통해 받아서 사용하도록 구현한다)

```java
public class Teacher {
		private static Teacher t = new Teacher(); // 객체 1개 유지를 위해 private
		private Teacher(){ // 외부에서 생성자 접근할 수 없도록 private
		}
		
			public static Teacher getTeacher() { // 객체없이 외부에서 접근가능하도록 static
			return t;
		}
}
```

```java
public class SingletonTest {
		public static void main(String[] args) {
				Teacher sc1 = Teacher.getTeacher();
				Teacher sc2 = Teacher.getTeacher();
				System.out.printf("두 객체는 같은가? %b%n", sc1==sc2);
		}
}
```

### 이유

먼저, 객체를 생성할 때마다 메모리 영역을 할당받아야 한다. 하지만 한번의 new를 통해 객체를 생성한다면 메모리 낭비를 방지할 수 있다.

또한 싱글톤으로 구현한 인스턴스는 '전역'이므로, 다른 클래스의 인스턴스들이 데이터를 공유하는 것이 가능한 장점이 있다.

### 사용

주로 공통된 객체를 여러개 생성해서 사용해야하는 상황

`데이터베이스에서 커넥션풀, 스레드풀, 캐시, 로그 기록 객체 등`

안드로이드 앱 : 각 액티비티 들이나, 클래스마다 주요 클래스들을 하나하나 전달하는게 번거롭기 때문에 싱글톤 클래스를 만들어 어디서든 접근하도록 설계

또한 인스턴스가 절대적으로 한 개만 존재하는 것을 보증하고 싶을 때 사용함

## 단점

객체 지향 설계 원칙 중에 `개방-폐쇄 원칙`이란 것이 존재한다.

만약 싱글톤 인스턴스가 혼자 너무 많은 일을 하거나, 많은 데이터를 공유시키면 다른 클래스들 간의 결합도가 높아지게 되는데, 이때 개방-폐쇄 원칙이 위배된다.

결합도가 높아지게 되면, 유지보수가 힘들고 테스트도 원활하게 진행할 수 없는 문제점이 발생한다.

또한, 멀티 스레드 환경에서 동기화 처리를 하지 않았을 때, 인스턴스가 2개가 생성되는 문제도 발생할 수 있다.

따라서, 반드시 싱글톤이 필요한 상황이 아니면 지양하는 것이 좋다고 한다. (설계 자체에서 싱글톤 활용을 원활하게 할 자신이 있으면 괜찮음)

<멀티스레드에서의 singleton 사용>👇👇

[https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/DesignPattern#singleton](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/DesignPattern#singleton)