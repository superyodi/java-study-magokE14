# 함수형인터페이스

- 1개의 추상 메소드를 갖는 인터페이스.
- Java8 부터 제공하는 기능으로 @FunctionalInterface 어노테이션을 붙여 Validation을 체크 해준다.

# 사용이유

- 람다식을 사용하기 위함
  - 람다는 함수형 인터페이스의 인스턴스를 만드는 방법.
- 인터페이스의 장점인 일관성 있는 프로그래밍을 제공.

# 함수형 프로그래밍
- 순수 함수를 조합하고 공유 상태, 변경 가능한 데이터 및 부작용을 피해 소프트웨어를 만드는 방식.
- 순수함수? -> 함수의 실행이 외부에 영향을 끼치지 않는 함수.
- 대입문을 사용하지 않는 프로그래밍.(작은 문제를 해결하기 위한 함수를 작성.)

# 함수형 인터페이스의 종류

- Supplier<T>
  - 매개변수는 따로 없고 반환값만 존재하는 함수형 인터페이스이다. Supplier는 T get() 이라는 추상메서드가 있다.
```Java
  @FunctionalInterface
  public interface Supplier<T>{
  T get();
  }
  Supplier<String> supplier = () -> "Supplier use"
  System.our.pirntln(supplier.get()); // Supplier user 출력
```
  
  
- Consumer<T>
  - 객체 T를 매개변수를 받아서 사용하고 반환값은 없는 함수형 인터페이스.
  - Consumer는 Void Accept(T t) 라는 추상메서드를 제공한다.
  - Consumer는 andThen 이라는 함수도 제공한다.
  - andThen을 사용하여 Consumer를 계속해서 사용할 수가 있다.
  
```Java
  @FunctionalInterface
  public interface Consumer<T>{
  
    void accept(T t);
  
    default Consumer<T> andThen(Consumer<? super T> after){
      Objects.requireNonNull(after);
      return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }
  }
//Hi, babo
//Nice to meet you!
```
  - 매개 변수로 전달 받은 값은 얕은 복사가 이루어 진다.(원본데이터에 영향을 안줌)
```Java
Consumer<String> string1 = s -> System.out.println(s.split(" ")[0]);
string1.andThen(System.out::println).accept("Hi, babo");

//Hi,
//Hi, babo
```
---
참고 자료 : https://devkingdom.tistory.com/292
