+ 함수형 인터페이스 (Functional Interface)
  + 추상 메소드를 딱 하나만 가지고 있는 인터페이스
  + SAM (Single Abstract Method) 인터페이스
  + @FuncationInterface 애노테이션을 가지고 있는 인터페이스

+ 람다 표현식 (Lambda Expressions)
  + 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
  + 코드를 줄일 수 있다.
  + 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.

+ 자바에서 함수형 프로그래밍
  + 함수를 First class object로 사용할 수 있다.
  + 순수 함수 (Pure function)
    + 사이드 이팩트가 없다. (함수 밖에 있는 값을 변경하지 않는다.)
    + 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
  + 고차 함수 (Higher-Order Function)
    + 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
+ 불변성

> 출처: [더 자바, Java 8](https://www.inflearn.com/course/the-java-java8/dashboard)


</div>

```java
package functional_interface;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {


    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}

```
```java
package functional_interface;

@FunctionalInterface
public interface RunSomething {

    //    void doIt();
    int doItInt(int number);

}


```
```java
package functional_interface;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Foo {
    public static void main(String[] args) {

        // 1) 익명 내부클래스 (anonymous inner class)
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("Hello");
//                System.out.println("Lambda");
//
//            }
//        };

        // 2) 람다 표현식
//        RunSomething runSomething = () -> {
//            System.out.println("Hello");
//            System.out.println("Lambda");
//
//        };


        // 3) 자바에서 함수형 프로그래밍
        // 순수함수: 파라미터에 같은 값을 넣으면 항상 리턴값이 동일해야 한다.
//        RunSomething runSomething = (number) -> {
//            return number + 10;
//        };
//
//        System.out.println(runSomething.doItInt(1)); // 11
//        System.out.println(runSomething.doItInt(1)); // 11
//        System.out.println(runSomething.doItInt(1)); // 11
//        System.out.println(runSomething.doItInt(1)); // 11
//        System.out.println(runSomething.doItInt(1)); // 11
//        System.out.println(runSomething.doItInt(1)); // 11

        // 3) -1. 3)의 반례
//        RunSomething runSomething = new RunSomething() {
//
//            int baseNumber = 10;
//
//            @Override
//            public int doItInt(int number) {
//                baseNumber++; // 함수 외부의 변수를 참조하거나 값을 바꾸려는 경우, 순수함수가 아니다.
//                return number + baseNumber;
//
//            }
//
//        };
//
//        System.out.println(runSomething.doItInt(1)); // 12
//        System.out.println(runSomething.doItInt(1)); // 13
//        System.out.println(runSomething.doItInt(1)); // 14
//        System.out.println(runSomething.doItInt(1)); // 15


        // 4) 함수형 프로그래밍을 안하더라도 람다표현식, 또는 익명 내부 클래스를 사용하고 싶은 경우 ==> 외부 변수 참조 가능

//        final int baseNumber = 10;
//        RunSomething runSomething = number -> number + baseNumber;
//        System.out.println(runSomething.doItInt(1));
//        System.out.println(runSomething.doItInt(1));
//        System.out.println(runSomething.doItInt(1));
//        System.out.println(runSomething.doItInt(1));

        // [자바에서 제공하는 함수형 인터페이스]
        // 1) Function<T, R>

        //● T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
        //  ○ R apply(T t)
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1)); // 11

        Function<Integer, Integer> funcPlus10 = (num) -> num + 10;
        System.out.println(funcPlus10.apply(1)); // 11

        //● F함수 조합용 메소드

        //  ○ compose
        Function<Integer, Integer> multiply2 = (num) -> num * 2;
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2)); // 14 (10 + 2*2)
        System.out.println(plus10.compose(multiply2).apply(2)); // 14

        //  ○ andThen
        System.out.println(plus10.andThen(multiply2).apply(2)); // 24 ( (10+2) * 2 )


        // 2) BiFunction<T,U,R>

        //● 두 개의 값(T, U)를 받아서 R 타입을 리턴하는 함수 인터페이스
        //  ○ R apply(T t, U u)

        // 3) Consumer<T>
        //● T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
        //  ○ void accept(T t)
        Consumer<Integer> printT = (num) -> System.out.println("result is " + num);
        printT.accept(10); // result is 10

        // 4) Supplier<T>
        //● T 타입의 값을 제공하는 함수 인터페이스
        //  ○ T get()
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get()); // 10

        // 5)  Predicate<T>
        //● T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
        //  ○ boolean test(T t)
        Predicate<Integer> test10 = (num) -> num == 10;
        Predicate<String> startsWithJuyeon = (s) -> s.startsWith("juyeon");
        System.out.println(startsWithJuyeon.test("juyeon hi~!")); // true
        System.out.println(startsWithJuyeon.test("jiyeon hi~!")); // false

    }
}
```
