# Thread

### Process

- 운영체제에 의해 메모리 공간을 할당받아 CPU에서 실행/제어되고 있는 프로그램
- 스케줄링의 대상이 되는 작업
- 데이터(data) + 자원(memory) + 스레드(Thread)로 구성되며 스레드가 데이터와 자원을 활용하여 작업을 수행한다.



### Thread

- Thread는 프로그램 내에서 실행되는 프로그램 제어 흐름(실행단위)를 말한다.

- Thread를 이용하여 한 개의  프로세스(process) 내에서 두가지 이상의 일을 **동시에** 할 수 있다.
- 프로그램 실행하면 JVM이 시작되고 JVM이 시작되면 자바 프로세스가 시작된다. 이때 프로세스 안에 스레드가 있다.
- **두 개 이상의 스레드를 가지는 프로세스**를 **멀티스레드** **프로세스**라고 한다.



### 사용 이유

- 메모리 절약(1MB 이내)
- 멀티 프로세스로 실행되는 작업에 비해 프로세스를 생성하여 자원을 할당하는 과정도 줄어들고, 프로세스를 콘텍스트 스위칭하는 것보다 오버헤드를 더 줄일 수 있게 된다.
- 작업들 간 통신 비용 절감(프로세스 간 통신 비용에 비해)



### 스레스와 프로세스의 차이점

- 프로세스는 메모리 영역을 다른 프로세스와 공유하지 않지만 스레드는 해당 스레드를 위한 스택을 생성할 뿐 프로세스 내의 메모리를 공유해서 사용할 수 있다.

![thread&process](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbO9SXa%2FbtqUEzmG6sn%2Fse1ISRdyR6tKAY1DdRUNK1%2Fimg.png)



### 생성방법

1. Thread class 상속
2. Runnable interface 구현



## Thread

```java
public class Sample extends Thread {
    public void run() {  // Thread 를 상속하면 run 메서드를 구현해야 한다.
        System.out.println("thread run.");
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.start();  // start()로 쓰레드를 실행한다.
    }
}
```



```java
public class Sample extends Thread {
    int seq;

    public Sample(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq + " thread start.");  // 쓰레드 시작
        try {
            Thread.sleep(1000);  // 1초 대기한다.
        } catch (Exception e) {
        }
        System.out.println(this.seq + " thread end.");  // 쓰레드 종료 
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {  // 총 10개의 쓰레드를 생성하여 실행한다.
            Thread t = new Sample(i);
            t.start();
        }
        System.out.println("main end.");  // main 메소드 종료
    }
}
```

```
0 thread start.
4 thread start.
6 thread start.
2 thread start.
main end.
3 thread start.
7 thread start.
8 thread start.
1 thread start.
9 thread start.
5 thread start.
0 thread end.
4 thread end.
2 thread end.
6 thread end.
7 thread end.
3 thread end.
8 thread end.
9 thread end.
1 thread end.
5 thread end.
```

- 쓰레드는 순서에 상관없이 동시에 실행된다.

- 쓰레드가 종료되기 전에 main 메서드가 종료된다.

##### join 메서드

```java
import java.util.ArrayList;

public class Sample extends Thread {
    int seq;
    public Sample(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq+" thread start.");
        try {
            Thread.sleep(1000);
        }catch(Exception e) {
        }
        System.out.println(this.seq+" thread end.");
    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Thread t = new Sample(i);
            t.start();
            threads.add(t);
        }

        for(int i=0; i<threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join(); // t 쓰레드가 종료할 때까지 기다린다.
            }catch(Exception e) {
            }
        }
        System.out.println("main end.");
    }
}
```

```
0 thread start.
5 thread start.
2 thread start.
6 thread start.
9 thread start.
1 thread start.
7 thread start.
3 thread start.
8 thread start.
4 thread start.
0 thread end.
5 thread end.
2 thread end.
9 thread end.
6 thread end.
1 thread end.
7 thread end.
4 thread end.
8 thread end.
3 thread end.
main end.
```

- Thread class를 상속받으면 다른 객체를 상속받을 수 없다.



## Runnable

```java
import java.util.ArrayList;

public class Sample implements Runnable {
    int seq;
    public Sample(int seq) {
        this.seq = seq;
    }

    public void run() {
        System.out.println(this.seq+" thread start.");
        try {
            Thread.sleep(1000);
        }catch(Exception e) {
        }
        System.out.println(this.seq+" thread end.");
    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++) {
            Thread t = new Thread(new Sample(i));
            t.start();
            threads.add(t);
        }

        for(int i=0; i<threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            }catch(Exception e) {
            }
        }
        System.out.println("main end.");
    }
}
```



##### 참고 

https://wikidocs.net/230

https://beststar-1.tistory.com/6
