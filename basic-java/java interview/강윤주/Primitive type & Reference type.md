### Primitive type

![Primitive type](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/e70394b7-697a-44a4-a8a6-6d91be474b2c/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220424%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220424T124536Z&X-Amz-Expires=86400&X-Amz-Signature=feee10336db7593376abf7b8be17c4dd29c4e761abc3594ff80a09106d9123a7&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

- JAVA에서는 총 8가지의 Primitive type을 미리 정의하고 제공합니다.
- 자바에서 기본 자료형은 반드시 사용하기 전에 선언(Declared)되어야 합니다.
- OS에 따라 자료형의 길이가 변하지 않습니다.
- **비객체** 타입입니다. 따라서 null 값을 가질 수 없습니다. 만약 Primitive type에 Null을 넣고 싶다면 Wrapper Class를 활용합니다.
- 스택(Stack) 메모리에 저장됩니다.

### Reference type

- JAVA에서 Primitive type을 제외한 타입들이 모두 Reference type 입니다.
- Reference type은 JAVA에서 최상인 java.lang.Object클래스를 상속하는 모든 클래스들을 말합니다. 물론 new로 인하여 생성하는 것들은 메모리 영역인 Heap 영역에 생성을 하게 되고, Garbage Collector가 돌면서 메모리를 해제합니다.
- **클래스 타입(class type)** , **인터페이스 타입(interface type)** , **배열 타입(array type)** , **열거 타입(enum type)** 이 있습니다.
- 빈 객체를 의미하는 Null이 존재합니다.
- 문법상으로는 에러가 없지만 실행시켰을 때 에러가 나는 런타임 에러가 발생합니다. 예를 들어 객체나 배열을 Null 값으로 받으면 NullPointException이 발생하므로 변수 값을 넣어야 합니다.
- Heap 메모리에 생성된 인스턴스는 메소드나 각종 인터페이스에서 접근하기 위해 JVM의 Stack 영역에 존재하는 Frame에 일종의 포인터(C의 포인터와는 다릅니다.)인 참조값을 가지고 있어 이를 통해 인스턴스를 핸들링합니다.