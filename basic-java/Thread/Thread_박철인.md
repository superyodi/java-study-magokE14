# 스레드란
- 프로세스 내에서의 작업 단위로서 시스템의 여러 자원을 할당받아 실행하는 프로그램의 단위

# 특징
- 스레드는 프로세스 내에서 각각 Stack만 할당받는다.
- 스레드간에 프로세스의 자원(heap, code, data등)을 공유할수 있다.

# 스레드의 실행제어
- NEW
  - 스레드가 생성되고 아직 start()가 호출되지 않은 상태
- RUNNABLE
  - 실행 중 또는 실행 가능 상태
- BLOCKED
  - 동기화 블럭에 의해 일시정지된 상태(lock이 풀릴 때까지 기다림)
- WAITING, TIME_WAITNG
  - 실행가능하지 않은 일시정지 상태
- TERMINATED
  - 스레드 작업이 종료된 상태

# 스레드 사용법 (Runnable)
- Runnable 인터페이스 구현
```Java
class MyThread implements Runnable {
@Ovveride
public void run(){
}
}
```
- Runnable 인터페이스를 구현한 경우는, 해당 클래스를 인스턴스화해서 Thread 생성자에 넘겨줘야 한다.
- Run()을 호출하면 인터페이스에서 구현한 run()이 호출 되므로 따로 오버라이딩하지 않아도 된다.
```Java
public static void main(String[] args){
Runnable r = new MyThread();
Thread t = new Thread(r, "myhtread");
}
```

# 스레드 사용법 (Thread class)
- Thread 클래스 상속
```Java
public class MyThread extends Thread {
@Ovveride
public void run(){
}
}
```

# 스레드 실행
- 실행은 run()이 아닌 start()로 해야
- run()을 사용하면 main의 콜 스택을 이용하므로 새롭게 만든 스레드를 사용하는 것이 아님.
- start() 메소드를 호출해 JVM이 새로운 콜 스택을 만들게해 스레드를 동작하게 해야함.
# 동기화(★)
- 멀티스레드 환경의 필수요소.
- 사용이유?
  - 여러 스레드가 같은 프로세스 내의 자원을 공유하면서 작업할 때 서로의 작업이 다른 작업에 영향을 주기 때문
- 임계 영역(Critical Section)과 잠금(lock)을 활용.
- lock은 하나의 스레드만 가질 수 있고, 사용이후 반납해야 함.

# 동기화 방법
- 임계영역(Critical Section)
  - 공유 자원에 단 하나의 스레드만 접근하도록(하나의 프로세스에 속한 스레드만 가능).
  - synchronized를 활용해 임계영역을 설정.
  - 서로 다른 두 객체가 동기화를 하지 않은 메소드를 같이 오버라이딩해서 이용하면,
		스레드가 동시에 진행되므로 원하는 출력 값을 얻지 못한다.
  - 따라서 오버라이딩 되는 부모 클래스의 메소드에 synchronized 키워드로 임계영역을 설정해준다.
```Java
public synchronized void saveMoney(int save){
int m = money;
try{
Thread.sleep(2000);
} catch (Exception e){
}
money = m + save;
System.out.println("입금 처리");
}

public synchronized void minusMney(int minus){
int m = money;
try{
Thread.sleep(3000);
}catch(Exception e){}
money = m -minus;
System.out.println("출금 완료");
}
```
- 뮤텍스(Mutex)
  - 공유 자원에 단 하나의 스레드만 접근 하도록(서로 다른 프로세스에 속한 스레드도 가능)함.
  - 스레드가 서로 협력 관계일 경우에 무작정 대기 시키는것
  - wait(), 스레드가 lock을 가지고 있으면, lock 권한을 반납하고 대기.
  - notify(), 대기 상태인 스레드에게 다시 lock 권한을 부여하고 수행하게 만듬.
  - 이 두 메소드는 임계영역내에서 사용되어야 함.
  - 동기화 처리한 메소드들이 반복문에서 활용된다면, 의도한대로 결과가 나오지 않는다.
  - 이때 try-catch 문에 wait()과 notify()를 넣어 로직을 구성.
- 이벤트(Event)
  - 특정한 사건 발생을 다른 스레드에게 알림
- 세마포어(Semaphore)
  - 한정된 개수의 자원을 여러 스레드가 사용하려고 할 때 접근 제한
- 대기 가능 타이머(Waitable Timer)
  - 특정 시간이 되면 대기 중이던 스레드 깨움
```Java
static boolean runFlag = false;
public synchronized void saveMoney(int save){
int m = money;
if(!runFlag){
try{
System.out.println("출금작업중.. 기다리세요");
wait();
runFlag=true;
} catch (Exception e){
}
}
money = m + save;
System.out.println("입금 처리");
runFlag= false;
}

public synchronized void minusMney(int minus){
int m = money;
if(!runFlag){
try{
System.out.println("입금작업중... 기다리세요");
wait();
runFlag=true;
}catch(Exception e){}
}
money = m -minus;
System.out.println("출금 완료");
runflag= false;
}
```
# 멀티스레딩
- 하나의 프로세스 안에 여러개의 스레드가 동시에 작업을 수행하는 것.

# 사용이유
- 자원의 효율성 증대
  - System Call(프로세스를 생성하여 자원을 할당하는)이 줄어들어 자원을 효율적으로 관리.
  - 프로세스의 자원을 공유하므로 효율적으로 자원을 관리.
- 처리 비용 감소 및 응답 시간 단축
  - IPC보다 스레드 간의 통신의 비용이 적다.
  - Context Switching시 스레드는 Stack 영역만 처리해 전환속도가 빠르다.


---
# 참고자료
- https://gmlwjd9405.github.io/2017/10/01/basic-concepts-of-development-os.html
- https://gyoogle.dev/blog/computer-language/Java/Thread.html
