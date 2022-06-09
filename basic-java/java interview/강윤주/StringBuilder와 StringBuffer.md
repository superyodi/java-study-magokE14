String : 불변.

```java
class HelloWorld {
	public static void main(String[] args) {
		String a = "hello";
		System.out.println(a + " : " + a.hashCode());
		a = a + " World!";
		String b = "hello";
		System.out.println(a + " : " + a.hashCode());
		System.out.println(b + " : " + b.hashCode());
	}
}
hello : 99162322
hello World! : -1133420035
hello : 99162322
```

위와 같이 변수 a의 값을 변경할 경우, “hello”는 GC의 대상이 되어  Garbage로 남아있다가 사라지게 된다.

변하지 않는 문자열을 빈번하게 사용하는 경우 String을 사용하는 경우 성능이 좋다.

하지만 연산이 빈번하게 사용되는 알고리즘에 String 클래스를 사용할 경우 Heap 메모리에 많은 임시 Garbage가 생성되어  Heap 메모리 부족으로 어플리케이션 성능을 저하시킬 수 있다.

StringBuffer&StringBuilder

`.append()` `.delete()` 등의 API를 이용하여 **동일 객체내에서 문자열을 변경**하는 것이 가능

```java
class HelloWorld {
	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("hello");
		System.out.println(a + " : " + a.hashCode());
		a = a.append(" World!");
		System.out.println(a + " : " + a.hashCode());
	}
}
hello : 1510467688
hello World! : 1510467688
```



|              | String        | StringBuffer | StringBuilder |
| ------------ | ------------- | ------------ | ------------- |
| Storage      | String pool   | Heap         | Heap          |
| Modifiable   | No(Immutable) | Yes(mutable) | No(Immutable) |
| Thread safe  | Yes           | Yes          | No            |
| Synchronized | Yes           | Yes          | No            |
| Performance  | Fast          | Slow         | Fast          |

**String**          : 문자열 연산이 적고 멀티쓰레드 환경일 경우

**StringBuffer**   **** : 문자열 연산이 많고 멀티쓰레드 환경일 경우

**StringBuilder**  : 문자열 연산이 많고 단일쓰레드이거나 동기화를 고려하지 않아도 되는 경우