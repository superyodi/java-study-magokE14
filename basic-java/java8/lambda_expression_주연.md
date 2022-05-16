# 람다 표현식

람다

+ (인자 리스트) -> {바디}

인자 리스트

+ 인자가 없을때 : ()
+ 인자가 한개일 때: (one) 또는 one
+ 인자가 여러개 일 때: (one, two)
+ 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다: (Interger one, Interger two)

바디

+ 화살표 오른쪽에 함수 본문을 정의한다. 
+ 여러 줄인 경우에 {}를 사용해서 묶는다.
+ 한 줄인 경우에 생략 가능, return도 생략 가능



변수 캡쳐(Variable Capture)

+ 로컬 변수 캡처

  + 참조하는 외부 변수가 사실상 final인 경우 (변수를 더이상 변경하지 않는 경우) final 생략할 수 있음


+ effective final

+ 익명 클래스 구현체와 달리 '쉐도잉'하지 않는다

  + 쉐도잉

    내부 클래스안에 외부 클래스와 같은 이름의 변수를 정의했을때, 덮어 써지는 것 









+ https://www.inflearn.com/course/the-java-java8/dashboard)


---


```java
package lambda_expression;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        // BiFunction<Integer, Integer, Integer> get10 = (x, y) -> 10;
        BinaryOperator<Integer> get10 = (a, b) -> a+b;

        UnaryOperator<Integer> plus10 = (i) -> i + 10;
        UnaryOperator<Integer> multiply2 = (i) -> i * 2;
        System.out.println(plus10.andThen(multiply2).apply(2)); // 24
        System.out.println(plus10.compose(multiply2).apply(2)); // 14

        // 로컬변수 캡쳐1
        Foo foo = new Foo();
        foo.run();

    }

    public static int test() {return 10;}
    private void run() {
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            int baseNumber = 11; // 이 변수가 위의 변수를 가림

            void printBaseNumber() {
                System.out.println(baseNumber); // 11
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // 더이상 외부 객체 참조 안하고 파라미터 객체 참조            }
            }
        };

            // 람다
            IntConsumer printInt = (baseNamber) -> {
                // 람다는 run()과 같은 스콥이다.
                // 파마리터와 내부 변수랑 같이 있을 수 없다

                System.out.println(baseNumber);
            };
            printInt.accept(10); // 여기서 baseNumner의 값이 capture됨


            // 자바8부터 참조하는 외부 변수가 사실상 final인 경우(변수를 더이상 변경하지 않는 경우, effective fianl), final 생략할 수 있음

            // 쉐도잉
        }

}

```
