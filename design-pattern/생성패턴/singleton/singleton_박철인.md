### 싱글톤패턴
- 애플리케이션이 시작 될때 어떤 클래스가 최초 한번만 메모리에 할당(static)하고 해당 메모리에 인스턴스를 만들어 사용하는 방법.
- 인스턴스는 한번만 생성되며 생성된 인스턴스를 공유하며 사용

### 사용이유와 장점
- 하나의 인스턴스만 사용하기 때문에 메모리 낭비를 줄일 수 있다.
- 싱글톤 패턴의 인스턴스는 전역(static)이기에 이를 통해 클래스간에 데이터를 공유할 수 있다.

### 주의사항
- 멀티스레드 환경에서는 적합하지 않는 패턴이다.
  - 여러개 스레드환경을 구축해놓고 하나의 자원을 사용하고자 하면 병목현상으로 대기시간만큼 성능이 저하 될수 있기 때문.
  - 동기화 처리를 하지 않는 경우 싱글톤패턴을 무시하고 2개 이상의 인스턴스가 생성될 수 있다.
- 싱글톤 인스턴스가 혼자 많은 일을 하거나 많은 데이터를 공유 시키면 "클래스간의 결합도가 높아짐".
  - 개방폐쇄원칙에 위배. 

### 구현방법1
1. 생성자에 private를 걸어 외부에서 생성하는 것을 막는다.
2. getinstance 메소드에 synchronized를 선언하여 동기화를 부여한다.
  - 객체 자체에 락을 거는 방법이기에 성능저하가 발생할 수 있다.

```java
public class LazyThread {

//	private synchronized LazyThread lazyins =null; 
//	클래스 변수에는 싱크를 달 수 없다. -> 
//  생성자 함수에도 달수 없었다.
	// -> 클래스의 멤버변수가 아니다. -> 리턴값을 설정하거나 상속받아 확장할수도 없다.
	// abstract, final, static 또한 사용할수 없음.
	// 그리고 생성자는 객체의 생성시에만 호출되므로 static일 필요가 없다.(생성자외엔 호출할 이유가 없다는 뜻)
	// 또한, 상속되지 않으므로 final이나 abstact을 가질수가 없다.
	// 생성시에 락을 발생하는 건 의미가 없으므로 synchronized할 필요도 없다.

	private static LazyThread lazyins =null;
	
	private LazyThread(){}; //암묵적으로 public이었던 생성자를 private으로 재선언하여 막음
	
	public static synchronized LazyThread getInstance() {
		if(lazyins==null) {
			lazyins = new LazyThread();
		}
		
		return lazyins;
	}
}
```

### 구현방법2
1. 생성자에 private를 걸어 외부에서 생성하는 것을 막는다.(공통)
2. getinstance 메소드가 아닌 하위 코드에 인스턴스가 null인 경우에만 인스턴스를 생성해준다.
3. 1번과 마찬가지로 코드로 동기화문제를 해결하므로 구조가 복잡해지고 정확도가 떨어질수있음
```java
//인스턴스가 없는경우에만 락을거는 방법.
//코드로 동기화문제를 해결하려할시 구조가 복잡해지고 정확도가 떨어질수있음
	public static LazyThread getInstance() {
		if(lazyins==null) {
			synchronized(LazyThread.class) {
				if(lazyins ==null) {
					lazyins = new LazyThread();
				}
			}
		}
		return lazyins;
	}
```

### 구현방법3
1. 내부클래스(holder)를 두어 JVM의 클래스 로더 매커니즘과 클래스가 로드 되는 시점을 이용하는 방법
2. JVM의 클래스 초기화 과정에서 보장되는 원자적 특성을 이용하여 싱글톤의 초기화 문제에 대한 책임을 JVM에게 떠넘김(정확도 개선)
3. 실제로 많이 사용됨.
``` java
// 클래스 안에 클래스(holder)를 두어 JVM의 클래스 로더 매커니즘과 클래스가 로드 되는 시점을 이용한 방법.
// JVM의 클래스 초기화 과정에서 보장되는 원자적 특성 을 이용해 싱글톤의 초기화 문제에 대한 책임을 JVM에게 떠넘김
// 실제로 많이 사용되는 방법이다.
public class Something {    
	private Something() {}
	private static class LazyHolder{
		public static final LazyThread lazyThread = new LazyThread(); // final로 선언된 상수는 로딩되지 않음.
	}	
	public static LazyThread getInstance() {
		return LazyHolder.lazyThread;
	}
```

### 느낀점
1. 클래스 변수에는 싱크를 달 수 없다.<br>
-> 메소드에만 달 수 있다.<br>
-> 생성자 함수에도 달수 없었다(메소드가 아니므로, 클래스의 멤버변수또한 아님)<br>
2. 생성자를 상속받아 확장할 수가 없다.<br>
-> 상속될수 없으니 final과 abstact도 달 수 없다.<br>
-> abstract, final, static 또한 사용할수 없음.(메소드가 아니기 떄문)<br>
-> 확장을 사용할 수 없는 패턴이니 개방폐쇄원칙에 위배된다.<br>

### 참고자료
- https://gyoogle.dev/blog/design-pattern/Singleton%20Pattern.html
