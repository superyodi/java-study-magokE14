# Thread (근데이제 자바를 곁들인)

## 스레드 개념 
   + 프로세스 안에  존재하는 작업 흐름 
   + 스레드는 각자의 data 영역을 가지고 heap 공간을 공유 
   + 멀티 스레드를 잘못 사용할 경우 deadlock (교착상태) 가 발생할 수 있음 
     + Thread-safe를 고려하여 코딩해야함 





## 자바 스레드 

### JVM 구조 



![image-20220610075354924](/Users/superyodi/Library/Application Support/typora-user-images/image-20220610075354924.png)

![image-20220610075615554](/Users/superyodi/Library/Application Support/typora-user-images/image-20220610075615554.png)

**JVM**

자바 바이트 코드를 실행하는 가상머신

여기서 여러가지 종류의 메모리 공간이 필요한데, 이때 사용하는 것이 Runtime DataArea

- Method Area, Heap Area: 모든 쓰레드가 공유하는 데이터 공간

  - Method Area
    - 클래스 로더가 클래스 파일을 읽어오면, 클래스 정보를 파싱해서 메소드 영역에 저장
  - Heap Area
    - 프로그램을 실행하면서 생성한 모든 객체 정보 저장

- Java stacks, PC registers, Native method stack: 각 스레드마다 존재

- Java Thread

  - Program Counter (PC register)

    - 각 스레드는 메서드를 실행하고 있고 pc는 그 메서드 안에서 바이트 코드 몇 번째 줄을 실행해야 하는지 나타내는 역할

  - Stack

    - 자바 스택은 스레드 별로 1개만 존재하고 스택 프레임은 메서드가 호출될때마다 생성된다

    - 메서드 실행이 끝나면 스택 프레임은 pop돼서 스택에서 제거된다.

      - Stack Frame
        - 스택 프레임은 메서드가 호출될때마다 새로 생겨 스택에 push된다
        - 스텍 프레임은 local variable array, Operand stack, Frame data 를 갖는다
        - Frame data
          - Constant Pool, 이전 스택 프레임에 대한 정보, 현재 메서드가 속한 클래스/객체에 대한 참조 등의 정보를 갖는다.
            - Constant Pool: 리터럴(상수) 값 저장.

    - 자바에서 스택으로 계산하는 이유

      - 레지스터 사용하지 않기 위해서
      - 레지스터 사용해서 계산하면 하드웨어의 제약을 받기때문에 스택으로 계산함
      - 코드 컴팩트해서 네트워크로 전송할때 깔끔
      - 하지만 안드로이드는 Dalvik 가상머신 써서 레지스터로 계산.
      - 참고 :

      [[Android\] JVM의 스택 기반 모델 vs DVM의 레지스터 기반 모델](https://s2choco.tistory.com/13)

  - Native method stacks

    - Java Byte code가 아닌 다른 언어로 작성된 메서드  (JVM 성능 확장을 목적)





> 덧, 자바에서 최대로 실행할 수 있는 스레드는 몇개일까?
>
> + Thread Pool
>
>   + Thread: 프로세스 내에서 실행되는 흐름의 단위 
>   + Pool: 필요할 때마다 개체를 할당하고 파괴하는 대신, 사용 준비된 상태로 초기화된 개체 집합
>
>   + 참고
>     + https://tecoble.techcourse.co.kr/post/2021-09-18-java-thread-pool/
>
> 



## dead lock 



### 데드락 발생 조건 

1. 상호배제( Mutual Exclusion ) : 한 자원에 대해서 여러 스레드 동시 접근 불가
2. 점유와 대기 ( Hold and Wait ) : 자원을 가지고 있는 상태에서 다른 스레드가 사용하고 있는 자원 반납을 기다리는 것 
3. 비선점  ( Non Preemptive ) : 다른 스레드의 자원을 실행 중간에 강제로 가져올 수 없음 
4. 환형대기  ( Circle Wait ) : 각 스레드가 순환적으로 다음 스레드가 요구하는 자원을 가지고 있는 것 (e.g 식사하는 철학자)

 

위 4가지 조건을 모두 만족해야 함 



### 데드락 상황 구현 

```java
package thread.deadLock;

// 멀티스레드에서 데드락이 발생하는 상황을 재현
public class Main {
    public static Object object1 = new Object();
    public static Object object2 = new Object();


    public static void main(String[] args) {

        FirstThread thread1 = new FirstThread();
        SecondThread thread2 = new SecondThread();

        thread1.start();
        thread2.start();

        /*
            object1은 FirstThread 에서 사용중
            object2은 SecondThread 에서 사용중
            FirstThread 는 object2 도 사용하길 기다리고 있음
            SecondThread 는 object1 도 사용하길 기다리고 있음

         */

    }


    private static class FirstThread extends Thread {
        @Override
        public void run() {
            // synchronized: 여러개의 스레드가 한개의 자원을 사용하고자 할때, 현재 데이터를 사용하고 있는 해당 스레드를 제외하고 
            //               나머지 스레드들은 데이터에 접근할 수 없도록 막는 개념 
            synchronized (object1) {
                System.out.println("object1은 FirstThread 에서 점유중");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("FirstThread 는 object2 도 점유하길 기다리고 있음");

                synchronized (object2) {
                    System.out.println("object2도 FirstThread 에서 점유중");
                }
            }
        }
    }

    private static class SecondThread extends Thread {
        @Override
        public void run() {
            synchronized (object2) {
                System.out.println("object2은 SecondThread 에서 사용중");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("SecondThread 는 object1 도 사용하길 기다리고 있음");

                synchronized (object2) {
                    System.out.println("object1도 SecondThread 에서 사용중");
                }
            }
        }
    }
}
```



#### 데드락 상황 해결 방안 

위에 있는 데드락이 발생하는 4가지 조건 중 하나만 만족시키지 않아도 탈출 가능하다 

> 참고: https://math-coding.tistory.com/175
