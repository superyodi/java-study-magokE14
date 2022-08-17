





### 동적인 객체 인스턴스 의존 관계

애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계 

+ 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 <u>의존관계 주입</u>이라고 한다
+ 객체 인스턴스를 생성하고 그 참조값을 전달해서 연결된다 
+ 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다. 
+ 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다. 





**IoC 컨테이너, DI 컨테이너**

+ AppConfig 처럼 객체를 생성하고 관리하면서 의존 관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너 라고 한다

  + 의존관계 주입에 초점을 맞추어 주로 `DI 컨테이너`라고 한다

    (*또는 어셈블러, 오브젝트 팩토리 등으로 불린다*)



### 스프링 컨테이너

+ ApplicationContext를 스프링 컨테이너라고 한다

+ 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고 DI 했지만 스프링 컨테이너가 해당 기능을 제공한다

+ 스프링 컨테이너는 `@Configuration`이 붙은 `AppConfig`를 구성 정보로 사용한다. 여기서 `@Bean` 이 적힌 메소드들을 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다 

  + 이렇게 등록된 객체를 **스프링 빈**이라고 한다 

+ 스프링 빈은 `@Bean`이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다 

  + e.g) memberService, orderService

+ 스프링 컨테이너를 통해서 맥락에 필요한 스프링 빈을 찾을 수 있다. 

  + `applicationContext.getBean()` 메서드를 통해 찾을 수 있다. 

+ **정리: ** 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용할 수 있다. 

  + 추측, 어떤 장점이 있을지 
    + 어노테이션을 사용하기 때문에 보일러 코드 줄어서 코드 짜기 쉬워짐 
    + 중복을 피해서 스프링 빈을 생성할 수 있다. 

  

### 스프링 컨테이너 생성 

스프링 컨테이너가 생성되는 과정 

  ```java
  ApplicationContext applicationContext = new AnnotaionConfigApplicationContext(AppConfig.class) // 어노테이션으로 스프링 컨테이너 생성하는 경우
  ```

  + ApplicationContext는 스프링 컨테이너고 인터페이스로 구현된다 
    + 사용자의 기호에 따라 XML로 구현할 수 있고, 어노테이션 기반의 자바 클래스로 구현할 수 있다 
  + 스프링 컨테이너를 생성할때는 구성 정보를 지정해 줘야한다 
    + 구성정보는 스프링 빈에 대한 정보를 말한다 
    + 여기서는 `AppConfig.class`



그 다음 스프링 컨테이너는 파라피터로 넣어준 구성 정보(AppConfig.class) 를 참고해서 스프링 빈을 등록하고 객체들 간의  의존관계도 설정한다. 

![스크린샷 2022-08-18 오전 1.14.11](/Users/superyodi/Library/Application Support/typora-user-images/스크린샷 2022-08-18 오전 1.14.11.png)



### BeanFactory와 ApplicationContext 



<img src="https://3513843782-files.gitbook.io/~/files/v0/b/gitbook-legacy-files/o/assets%2F-LxjHkZu4T9MzJ5fEMNe%2Fsync%2F21012b333f698d2d366ad35304db7e559cd641d9.png?generation=1618052456312074&alt=media" alt="BeanFactory와 ApplicationContext - dodeon" style="zoom:67%;" />



**BeanFactory**

+ 스프링 컨테이너의 최상위 인터페이스

+ 스프링 빈을 관리하고 조회하는 역할 담당

+ `getBean()` 제공

  

**ApplicationContext**

+ BeanFactory와의 차이 
  + BeanFactory 기능 외 부가적인 기능을 개발하고자 할때 

**ApplicationContext가 제공하는 부가기능**

![BeanFactory와 ApplicationContext - dodeon](https://3513843782-files.gitbook.io/~/files/v0/b/gitbook-legacy-files/o/assets%2F-LxjHkZu4T9MzJ5fEMNe%2Fsync%2Fad9b87e9ee7590f5f82a2bc0e554d33c87cd91b5.png?generation=1618052455370924&alt=media)





---

> 덧, 팩토리 메소드 패턴 





# 팩토리 메소드 패턴



### 팩토리 메소드 패턴 

객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브클래스(자식클래스)에서 결정하도록 함 

+ 상위 클래스에 알려지지 않은 구현 클래스를 생성 
+ 하위 클래스에서 어떤 객체를 생성할지 결정 



### 사용에 적합한 상황 

+ 부모 클래스를 구현하는 자식 클래스를 숨기고 싶은 경우 
+ 클래스의 인스턴스를 서브클래스에서 결정하도록 하고싶을 경우 



### Spring DI에서의 팩토리 패턴

팩토리 메소드 패턴은 Spring DI 컨테이너에서 사용된다. 

스프링은 Bean을 생상하는 <u>Bean Container를 Bean을 생산하는 Factory로 취급</u>하기 때문에 스프링 컨테이너는 다양한 형식의 appConfig를 설정할 수 있도록 설계되어있다. 



![images%2Fweekbelt%2Fpost%2Fc9cfe500-9285-439c-a617-dd37af10befc%2Ffactorymethod01](https://velog.velcdn.com/images%2Fweekbelt%2Fpost%2Fc9cfe500-9285-439c-a617-dd37af10befc%2Ffactorymethod01.png)



> This interface is implemented by objects that hold a number of bean definitions, each uniquely identified by a String name. Depending on the bean definition, the factory will return either an independent instance of a contained object (the Prototype design pattern), or a single shared instance (a superior alternative to the Singleton design pattern, in which the instance is a singleton in the scope of the factory). Which type of instance will be returned depends on the bean factory configuration: the API is the same. Since Spring 2.0, further scopes are available depending on the concrete application context (e.g. "request" and "session" scopes in a web environment).



appConfig.xml,  AppConfig.class 등 다양한 방식에서 Bean이 정의되어도 동일한 방식으로 Bean이 생성되어야 하는데 이를 구현하기 위해 Factory 패턴이 사용되었다.  



```java
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType, Object... args) throws BeansException;
    
    //...
}
```



> 참고자료
>
> + https://velog.io/@weekbelt/%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CFactory-Method-%ED%8C%A8%ED%84%B4
>
> + https://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch02s02.html
>
> + 
>









---

### 싱글톤 컨테이너 



스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서 객체 인스턴스를 싱글톤으로 관리한다 



#### 싱글톤 컨테이너

+ 스프링 컨테이너는 싱글턴 컨테이너 역할을 한다 
  + 싱글톤 객체를 생성하는 관리하는 기능을 싱글톤 레지스토리 
+ 싱글톤 패턴의 단점 해결 
  + 싱글톤 패턴의 단점은 싱글톤 구현 클래스에 대한 클라이언트의 의존도가 높아지면서 발생한다
  + 싱글톤 컨테이너는 외부에서 빈을 주입하기 때문에 클라이언트가 싱글톤 객체(빈)에 의존하지 않는다 



> 스프링의 기본 빈 등록 방식은 싱글톤이지만 싱글톤만 지원하는건 아니다. 

