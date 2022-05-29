# Stream

Java 6 이전까지는 컬렉션의 엘리먼트들을 순회하기 위해 Iterator 객체를 이용했다.

```java
List<String> list = new ArrayList<String>(Arrays.asList("a","b","c"));

Iterator<String> iterator = list.iterator();

While(iterator.hasNext()) {
	String value = iterator.next();
  	if(StringUtils.equals(value, "b")) {
      System.out.println("값 : " + value);
    }
}
```

Java 8부터는 Stream을 사용하여 더 단순하게 코드를 작성할 수 있다.

```java
List<String> list = new ArrayList<String>(Arrays.asList("a","b","c"));
list.stream().filter("b"::equals).forEach(System.out::println);
```

 참고 : https://hbase.tistory.com/171