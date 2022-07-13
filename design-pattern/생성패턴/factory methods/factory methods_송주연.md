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
>   









