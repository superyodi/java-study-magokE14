# Design Pattern
  : 일종의 설계 기법이며, 설계 방법이다.
  
# 목적
  : SW 재사용성, 호환성, 유지 보수성을 보장.
  
# 특징
  : 디자인 패턴은 특정한 구현이 아닌 하나의 아이디어.
  
  프로젝트에 항상 적용해야 하는 것은 아니지만, 추후 (재사용, 호환, 유지 보수시) 발생하는 문제해결을 예방하기 위해 패턴을 만들어 둔 것.
  
# SOLID 객체지향 설계 원칙

1. Single Responsibility Principle(단일 책임 원칙) <br>
    하나의 클래스는 하나의 역할만을 가지도록 설계해야 한다.<br>
    어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다.<br>
    (책임 = 변경사유, 해야하는일)<br>
    같은 이유로 변화하는 것끼리 묶고, 다른 이유로 변화하는 것끼리는 분리하라.<br>
    (응집도를 높이면서 결합도는 낮추는)<br>
    
```Java
    public class Student { 
    public void getCourses() { ... } 
    public void addCourse(Course c) { ... } 
    public void save() { ... } 
    public Student load() { ... } 
    public void printOnReportCard() { ... } 
    public void printOnAttendanceBook() { ... }
    }
    
```
  
    
```Java
    
    public class Student { 
    public void getCourses() { ... } 
    public void addCourse(Course c) { ... }
    }
    
```
    
수강과목(Course)을 불러오고 조회하는 책임만을 부여하여 SRP를 적용시킨다.<br>
Student 클래스가 수정되어야 한다면 그 사유는 수강과목을 조회하고 불러오는 일과 관련되어야 한다.<br>
    
* * *
2. Open Close Principle (개방 폐쇄 원칙) <br>
    - 확장 (상속)에는 열려있고, 수정에는 닫혀 있어야 한다.<br>
    - 기존의 코드를 변경하지 않으면서 새로운 기능을 추가할 수 있도록 설계하는 원칙.<br>
    <br>
    - 무엇이 변하는 것인지, 무엇이 변하지 않는 것인지를 구분하여,<br>
    - 변해야 하는 것은 쉽게 변할 수 있게 하고<br>
    - 변하지 않아야 할 것은 변하는 것에 의해 영향을 받지 않게 해야 한다.<br>

```Java
    public class Student { 
    public void getCourses() { ... } 
    public void addCourse(Course c) { ... } }
```
    
수강과목을 추가할때 담당교수에게 메일이 가도록 추가하고 싶다면?
    
Student 클래스의 코드를 수정하는 것보다,<br>
애초에 생성할때 추상클래스(추상메소드 포함)로 선언해 주는것이 좋다.<br>
그래야 추가기능을 넣어주고 싶을때 이를 확장하여 기존의 코드를 건드는 일이 없음.<br>
    
```Java
    public abstract class Student { 
    public void getCourses() { ... } 
    public abstract void addCourse(Course c) { ... } }
```
    
#### <클래스를 변경하지 않고도(closed) 그 클래스를 둘러싼 환경을 변경할 수 있는(open) 설계.>

* * *
3. Liskov Substitution Principle (리스코프 치환 원칙) <br>
    (상속관계에서) 자식이 부모의 자리에 항상 교체될 수 있어야 한다.<br>
    부모 클래스와 자식 클래스 사이의 행위가 일관성이 있어야 한다.
    
```Java
    public class Rectangle {
    protected double width;
    protected double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(final double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }
}
```

```Java
//정사각형 클래스
package solid.lsp;

public class Square extends Rectangle {
    @Override
    public void setWidth(final double width) {
        this.height = width;
        this.width = width;
    }

    @Override
    public void setHeight(final double height) {
        this.height = height;
        this.width = height;
    }
}
```

```Java
package solid.lsp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    @Test
    void 직사각형이_제대로_넓이를_구하는지_테스트() {
        /* Given */
        Rectangle rectangle = new Rectangle();
        /* When */
        rectangle.setHeight(4);
        rectangle.setWidth(5);
        /* Then */
        assertThat(rectangle.getArea()).isEqualTo(20);
    }

    @Test
    void 정사각형이_제대로_넓이를_구하는지_테스트() {
        /* Given */
        Rectangle rectangle = new Square();
        /* When */
        rectangle.setHeight(4);
        rectangle.setWidth(5);
        /* Then */
        assertThat(rectangle.getArea()).isEqualTo(20);
    }
}
```

### 2번 테스트는 실패 왜?
정사각형 클래스는 부모의 클래스를 오버라이딩함 -> 리스코프원칙위배<br>
오버라이딩한 두함수를 이용할때마다 변경된 함수가 동작.<br>
    
따라서, 여기서는 확장 기능을 사용하지 않거나 자식클래스에게 getArea를 작성하도록 하여 LSP 원칙을 지킨다.<br>
<br>
<br>

* * *
4. Interface Segregation Principle(인터페이스 분리 원칙) <br>
    인터페이스가 잘 분리되어서, 클래스가 꼭 필요한 인터페이스만 구현하도록 해야한다.<br>
    클라이언트(자식)는 자신이 사용하지 않는 메소드에 의존 관계를 맺으면 안된다.
    
![image](https://user-images.githubusercontent.com/43160639/164980178-3197a217-0508-4811-98aa-ae73992d5ddc.png)


* * *

5. Dependency Inversion Property(의존성 역전 원칙) <br>
  
   - 의존 관계를 맺을 때 변화하기 쉬운 것 또는 변화가 자주 되는것보다는 <br>
   - 변화하기가 어려운 것, 변화가 거의 되지 않는 것에 의존하는 원칙
- IoC (Inversion Of Control)
  - 제어의 역전 : 외부에서 결정 되는 것.
  - 프레임워크의 내부에서 결정된 대로 이뤄지는 것.
- DI (Dependency Injection)
  - 의존성 주입 : 필요한 객체를 직접 생성하는 것이 아닌 외부 XML로부터(제 3자) 객체를 받아 사용 하는것.
  - 즉, 제 3자가 객체를 주입한다. (Setter 또는 생성자로)

* 변하기 어려운 것 
    + 추상적인 것, 추상클래스, 인터페이스
      
* 변하기 쉬운 것 :
    + 구체적인 방식, 사물 등
      
```Java
public class Kid { 
private Toy toy; // Toy는 abstract. 즉 변하기 어려운 것이다. 
public void setToy(Toy toy){ 
this.toy = toy; } 
public void play(){ 
System.out.println(toy.toString()); } 
}
```

 Kid 객체와 의존 관계를 맺고 있는 Toy 객체를 다른 객체로 바꾸고 싶다면(로봇이나 인형같은)<br>
 Toy를 상속받는 또 다른 객체를 생성하여 의존 객체를 변경할 수 있다.<br>
    <br>
 상위 모듈이 하위 모듈에 의존하면 안되고 추상화에 의존하며,<br>
 추상화는 세부사항에 의존하면 안된다.<br>
 
 * * *   
 참고자료 : https://gyoogle.dev/blog/design-pattern/Overview.html <br>
 https://starkying.tistory.com/entry/소프트웨어-설계-기법-SOLID란 <br>
 https://huisam.tistory.com/entry/LSP
    
