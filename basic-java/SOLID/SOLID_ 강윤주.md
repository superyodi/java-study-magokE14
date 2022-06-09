# **SOLID**

### **객체지향 설계 5원칙**

- `SRP(Single Responsibility Principle): 단일 책임 원칙`
- `OCP(Open Closed Priciple): 개방 폐쇄 원칙`
- `LSP(Listov Substitution Priciple): 리스코프 치환 원칙`
- `ISP(Interface Segregation Principle): 인터페이스 분리 원칙`
- `DIP(Dependency Inversion Principle): 의존 역전 원칙`

### **SRP(Single Responsibility Principle)**

> 어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다.

### **OCP(Open Closed Principle)**

> 소프트웨어 엔티티(클래스, 모듈, 함수 등)는 확장에 대해서는 열려 있어야 하지만 변경에 대해서는 닫혀 있어야 한다.

### **LSP(Listov Substitution Principle)**

> 서브 타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다.

### **ISP(Interface Segregation Principle)**

> 클라이언트는 자신이 사용하지 않는 메소드에 의존 관계를 맺으면 안 된다.

### **DIP(Dependency Inversion Principle)**

> 고차원 모듈은 저차원 모듈에 의존하면 안 된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다. 추상화된 것은 구체적인 것에 의존하면 안 된다. 구체적인 것이 추상화된 것에 의존해야 한다. 자주 변경되는 구체(Concrete) 클래스에 의존하지 마라

참고 : https://www.nextree.co.kr/p6960/