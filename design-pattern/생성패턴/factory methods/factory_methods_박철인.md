# <span style="color:red">추상</span>팩토리<span style="color:blue">메소드</span>패턴

### 팩토리 메소드 패턴(추상 메소드 패턴)
- 객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브클래스에서 결정하게 만든다.
- 즉 팩토리 메소드 패턴을 이용하면 클래스의 인스턴스를 만드는 일을 서브클래스에게 맡기는 것.
- 하나의 추상클래스에서 추상 메소드를 만들고 서브클래스들이 그 추상메소드를 구현하여 인스턴스를 만들게끔 하는것.
- DIP(의존성역전원칙)이 두드러지는 패턴
  - 코드를 수정해야할 일이 적어지고 다형성이 부각됨
  - 바뀔 수 있는 부분을 찾아내서 바뀌지 않는 부분하고 분리시키기 때문.

##### 추상 팩토리 패턴
- 인터페이스를 이용하여 서로 연관된, 또는 의존하는 객체를 구상 클래스를 지정하지 않고도 생성
- 제품군을 생성하기 위한 인터페이스를 생성 그 인터페이스를 구성하여 사용할수 있게끔 하는것. 


### 예제
- 객체를 생산하는 생산자 클래스
![image](https://user-images.githubusercontent.com/43160639/178704211-b53d47c1-971b-4101-9bda-dd706e74d959.png)

- 제품을 생산하는 제품 클래스
![image](https://user-images.githubusercontent.com/43160639/178704725-b951b999-e811-4a8d-8743-619f0db94113.png)

- (피자를 만드는) 활동 자체를 하나의 클래스(PizzaStore)에 국한시키면서 분점마다 고유의 스타일을 살릴 수 있는 방법.
```java
public abstract class PizzaStore //팩토리메서드패턴(abstact 추가)
// 피자가게와 피자 제작과정 전체를 하나로 묶어주는 프레임워크를 만듦
{
    /* 삭제
    SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory){
        this.simplePizzaFactory = simplePizzaFactory;
    }
    */

    public Pizza orderPizza(String type){
        Pizza pizza;

        pizza = simplePizzaFactory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstact Pizza createPizza(String type); //Pizza 인스턴스를 만드는 일은 팩토리 역할을 하는 메소드 에서 맡아 처리 , 팩토리메서드
}
```
```java
public class NYPizzaStore extends PizzaStore
{
    @Override
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equals("cheese")) pizza= new NYstyleCheesePizza();
        if(type.equals("peper")) pizza = new NYStylePepperoniPizza();
        if(type.equlas("clam")) pizza= new NYStyleClamPizza();
        if(type.equlas("veggie")) pizza = new NYStyleVeggiePizza();
        return pizza;
    }
}
```

```java
public class ChicagoPizzaStore extends PizzaStore
{
    @Override
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equlas("cheese")) pizza=new NYStyleCheesePizza();
        if(type.equlas("peper")) pizza = new NYStylePepperoniPizza();
        if(type.equlas("clam")) pizza = new NYStyleClamPizza();
        if(type.equlas("veggie")) pizza= new NYStyleVeggiePizza();
        return pizza;
    }
}
```

```java
public abstract class Pizza
{
    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();

    public void prepare(){
        System.out.println("Preparing :"+name);
        System.out.println("Tossing dough...");
        System.out.println("Adding source");
        System.out.println("Adding toppings");
        for(String topping : toppings){
            System.out.println("\ttopping : " + topping);
        }
    }

    public void bake(){
        System.out.println("Bake for 25 minu at 350");
    }

    public void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName(){
        return this.name;
    }
}
```

```Java
public class NYStyleCheesePizza extends Pizza{
    public NYStyleCheesePizza(){
        this.name = "NY Style CheesePizza";
        this.dough = "Thin Crust Dough";
        this.sauce = "Marinara Sauce";
        this.toppings.add("Grated Reggiano chesse");
    }
}
```

```Java
public class ChicagoStyleCheesePizza extends Pizza{
    public ChicagoStyleCheesePizza(){
        this.name= "Chicago Style CheesePizza";
        this.doguh = "Extra Thick Crust Dough";
        this.sauce = "Plum Tomato Sauce";
        this.toppings.add("Shredded mozzarella Cheese");
    }

    @Override
    public void cut(){
        System.out.println("cutting the pizza into sqaure slices");
    }
}
```

```Java
public class PizzaTestDrive{
    public static void main(String[] args){
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaSotre();
        
        Pizza nySytpePizza = nyStore.orderPizza("cheese");
        System.out.println(nySytpePizza.getname());
        System.out.println();
        Pizza chicagoStypePizza = chicagoStore.orderPizza("cheese");
        System.out.println(chicagoStypePizza.getName());
    }
}
```

### DIP를 지키기위해 도움이되는 가이드
- 어떤 변수에도 구상 클래스에 대한 레퍼런스를 지정하지 않는다.
  - new 연산자를 사용하면 레퍼런스를 사용하게 되는것
- 구상 클래스에서 유도된 클래스를 만들지 않는다.(추상화 된것을 사용)
  - 구상클래스에서 유도된 클래스를 만들면 특정 구상 클래스에 의존하게된다.
- 베이스 클래스에 이미 구현되어 있던 메소드를 오버라이드 하지 않는다.
  - 이미 구현되어 있는 메소드를 오버라이드 한다는 것은 애초부터 베이스 클래스가 제대로 추상화 된것은 아니었다고 볼 수 있다.
  - 베이스 클래스에서 메소드를 정의할 때는 모든 서브 클래스에서 공유할 수 있는 것만 정의
- 자신이 만들고 있는 클래스가 바뀔 가능성이 있다면 팩토리 메소드 패턴 같은 기법을 써서 변경될 수 있는 부분을 캡슐화 한다.

#### 참고자료
- https://jusungpark.tistory.com/14 팩토리패턴

