# Stream API

### 개념

#### Stream API

+ 연속된 데이터를 처리하는 오퍼레이션들의 모음

+ 컬렉션은 데이터를 가지고 있는 것, 스트림은 지나가면서 처리하는 것

  + 컨베이어 벨트에 데이터를 흘려보내면서 처리를 하는 것, 그 자체가 스트림 

+ Functional in nature: 원본데이터를 변경하지 않는다

+ 스트림이 제공하는 API 종류

  1. 중개

     1. **스트림을 return**

     2. lazy 하다

        종료(터미널) 오퍼레이션이 올때까지 처리하지 않는다. 

     3. ex)

        ` map, filter, sorted 등`

  2. 종료

     1. **다른 타입(콜렉션 등)을 리턴**

     3. ex)

        `collect, allMatch, forEach, count 등` 

+ **손쉽게 병렬처리 할 수 있다**
  + 스트림의 경우 `parrallelStream()` 을 받아서 처리하면 JVM에서 알아서 병렬처리한다
  + 하지만 병렬처리는 항상 빠른 속도를 보장하진 않는다
    + 스레드 생성, 삭제 과정 중 드는 바용
    + 스레드간의 Context Swithcing에 드는 비용
  + 병렬처리를 고려해야 할 경우는 처리해야 할 데이터가 방대하게 클 경우 
    + 데이터 케이스마다 성능측정을 해봐야한다




---

### 실습코드



```java
package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("juyeon");
        names.add("song");
        names.add("superyodi");

        // names.stream().map(String::toUpperCase).forEach(System.out::println); // 이 결과는 또 다른 스트림이 되는 것, 저 안에 있는 데이터들은 변하지 않음

        // return type 단축키, 커멘드+옵션+v
        Stream<String> stringStream = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });// 이 코드 실행 안함

        names.stream().map((s) -> {
           // System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("-------------------------");
        
        for(String name: names) {
            if ( name.startsWith("s")) {
                System.out.println(name.toUpperCase());
            }
        } // 이런 경우 병렬적으로 처리하기 어려움

        System.out.println("-------------------------");

        List<String> collect = names.parallelStream()
                .filter(s -> s.startsWith("s"))
                .map(s -> {
                    System.out.println(s + ": " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }).collect(Collectors.toList());
        /*
            song: main
            superyodi: ForkJoinPool.commonPool-worker-1
         */

        collect.forEach(System.out::println);

        // 병렬적으로 처리하는지 확인하기 위한 코드

    }
}

```



---



### 연습문제

> 1. 8진수 2진수 https://www.acmicpc.net/problem/10809
> 2. 알파벳찾기
> https://www.acmicpc.net/problem/1212
> 3. 복호화
> https://www.acmicpc.net/problem/9046







```java
package stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int TC = Integer.parseInt(br.readLine());
        int tc = 0;

        while (tc < TC)  {
            String s = br.readLine();
            System.out.println(boj9046(s));
            tc++;
        }
        // boj10809(br.readLine());
    }

    // 알파벳 찾기
    static void boj10809(String s) {
        Integer[] alphas = new Integer[26];
        Arrays.fill(alphas, -1);

        char[] sArr = s.toCharArray();

        IntStream
                .range(0, sArr.length)
                .forEach(i -> {
                    int alpha = sArr[i] - 'a';
                    if(alphas[alpha] == -1) alphas[alpha] = i;
                });

        List<Integer> alphaList = new ArrayList<>(Arrays.asList(alphas));
        alphaList.stream()
                .map(i -> i.toString())
                .forEach(it -> System.out.printf("%s ", it));
    }

    // 복호화
    static String boj9046(String s) {
        Integer[] alphas = new Integer[26];
        Arrays.fill(alphas, 0);

        AtomicInteger maxCnt = new AtomicInteger();
        char[] sArr = s.replaceAll(" ", "").toCharArray();

        IntStream
                .range(0, sArr.length)
                .forEach(i -> {
                    int alpha = sArr[i] - 'a';
                    alphas[alpha]++;
                    maxCnt.set(Integer.max(maxCnt.get(), alphas[alpha]));
                });

        List<String> collect = IntStream
                .range(0, 26)
                .filter(i -> alphas[i] == maxCnt.get())
                .mapToObj(i -> Character.toString((char) (i + 'a')))
                .collect(Collectors.toList());

        if(collect.size() > 1) { return "?"; }
        return collect.get(0);

    }
}
```

