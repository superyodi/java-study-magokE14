# Stream API
- 데이터의 흐름
- 연속된 데이터를 처리하는 오퍼레이션들의 모음
- 스트림은 컬렉션을 필요에 맞게 처리하는 과정.(하지만 원본 데이터에 영향을 주지않음.)
	- 컬렉션은 데이터를 구조화하여 젖아하고 다루는것.
# 사용이유?
- 멀티쓰레드 환경에서 병렬프로그래밍을 하기 위함.
# 프로세스
- 스트림 생성 -> 필터링, 매핑 -> 결과

# 병렬처리가 빠를까?
- 스레드는 생성하고 삭제하는데 비용이 발생.
- 프로세스의 상태에 따라 Context Switching이 발생.
- 이러한 스레드의 환경을 이해하고 효율적으로 다뤄야 속도를 기대할 수 있다.
# 알파벳찾기
```Java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;
class Main {
    
	static Map<Character, Integer> m = new HashMap<>();
	static int idx = 0;
	
	static void insert(char c) {
		if(!m.containsKey(c)) {
			m.put(c,idx);
		};
		idx++;
	}
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        
//        for (int i = 0; i < input.length(); i++) {
//            if(!m.containsKey(input.charAt(i)))
//            m.put(input.charAt(i),i);
//        }
        
        char[] inputChar = input.toCharArray();
        Stream<Character> inputStream = String.valueOf(inputChar)
        .chars().mapToObj(c -> (char) c);
        inputStream.forEach(i -> insert(i));
        
//        for (char i = 'a'; i <= 'z'; i++) {
//            int idx = -1;
//            if(m.containsKey(i)){
//                idx = m.get(i);
//            }
//            System.out.print(idx+" ");
//        }
        
       
        Stream<Character> outputStream = Stream.iterate('a', c -> (char)(c+1)).limit('z'-'a'+1);
        outputStream.forEach(i -> printFun(i));
        
        
    }
    static void printFun(char c) {
    	int idx = -1;
      if(m.containsKey(c)){
          idx = m.get(c);
      }
      System.out.print(idx+" ");
    }
}
```

# 8진수 2진수
```Java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

//        for (int i = 0; i < input.length(); i++) {
//            if(i==0){
//                System.out.print(Integer.toBinaryString(input.charAt(i)-'0'));
//            }else{
//                String tmp = Integer.toBinaryString(input.charAt(i)-'0');
//                if(tmp.length()==2){
//                    tmp="0"+tmp;
//                }else if(tmp.length()==1){
//                    tmp="00"+tmp;
//                }
//                System.out.print(tmp);
//            }
//        }
        
        //8진수 to 10진수 to 2진수

        //N진수를 10진수로 parseLong
        
        //하나의 flow로
        System.out.print(Integer.toBinaryString(input.charAt(0)-'0'));
        
        char[] charArray = input.toCharArray();
//        Stream<Character> inputStream = input
//        		.chars()
//        		.filter((i)->i!=0)
//        		.mapToObj(c -> (char)c);
        IntStream.range(0,charArray.length)
        .filter(i->i>=1)
        .mapToObj(i -> charArray[i])
        .peek(x -> printZero(x))
        .forEach(x -> System.out.print(Integer.toBinaryString(x-'0')));
 
    }
    static void printZero(char x) {
    	String tmp = Integer.toBinaryString(x-'0');
    	int len = tmp.length();
    	if(len==2)System.out.print("0");
    	else if(len==1)System.out.print("00");
    }
}
```
# 복호화
```Java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.IntStream;


class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		char res[] = new char[tc];
		

		for (int i = 0; i < tc; i++) {
			Map<Character, Integer> m = new HashMap<>();
			String input = br.readLine();
//			
			
//			char[] input2 = input.toCharArray();
//			for (int j = 0; j < input2.length; j++) {
//				m.put(input2[j], m.getOrDefault(input2[j], 0)+1);
//			}
//			
			input.replaceAll(" ","").chars().mapToObj(c->(char)c).forEach(c -> m.put(c,m.getOrDefault(c,0)+1));
			int max = -1;
			for(Entry<Character, Integer> f : m.entrySet()) {
				max=Math.max(max,f.getValue());
			}
//			m.entrySet().stream().forEach(e->max=Math.max(max,e.getValue()));
			int cnt = 0;
			
			for (char z : m.keySet()) {
				int value = m.get(z);
				if(max==value) {
					cnt++;
					res[i]=z;
				}
			}
			
			if(cnt>=2)res[i]='?';
		}
//		for(char i : res) {
//			System.out.println(i);
//		}
//		Arrays.stream(res).mapToObj(c->(char)c).forEach(System.out::println);
		IntStream.range(0, res.length).mapToObj(i->res[i]).forEach(System.out::println);

	}
}
```

# 추가연습

```Java
System.out.println("1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리.");
//변수에 담기.
List<Transaction> result = transactions.stream()
.filter(i->i.getYear()==2011)
.sorted(Comparator.comparing(Transaction::getValue))
.collect(Collectors.toList());

//그냥 뱉기
transactions.stream()
.filter(i->i.getYear()==2011)
.sorted(Comparator.comparing(Trasaction::getValue))
.forEach(i->System.out.println(i.toString()));

System.out.println();
System.out.println("2. 거래자가 근무하는 모든 도시를 중복없이 나열.");
transactions.stream()
.map(Transaction::getTrader)
.map(Trader::getCITY)
.distinct()
.forEach(System.out::println)

System.out.println();
System.out.println("3. Cambridge에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬.");
transactions.stream()
.map(Transactions::getTrader)
.filter(i->i.getCITY().equals("Cambridge"))
.sorted(Comparator.comparing(Trader::getName))
.map(Trader::getName)
.forEach(System.out::println);

System.out.println();
System.out.println("4. 모든 거래자의 이름을 알파벳순으로 정리해서 반환");
transactions.stream()
.map(Transaction::getTrader)
.sorted(Comparator.comparing(Trader::getName))
.map(Trader::getNmae)
.distinct()
.forEach(System.out::println);

System.out.println();
System.out.println("5. 밀라노에 거래자가 있는가?");
if(transactions.stream()
.map(Transaction::getTrader)
.map(Trader::getCITY)
.anyMatch(i->i.equals("Milan"))) System.out.println("ㅇㅇ")
else System.out.println("ㄴㄴ");

System.out.println();
System.out.println("6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.");
transactions.stream()
.filter(i->i.getTrader().getCITY().equals("Cambridge"))
.map(Transaction::getValue)
.forEach(System.out::println)

System.out.println();
System.out.println("7. 전체 트랜잭션 중 최댓값은 얼마인가?");
transactions.stream()
.map(Transaction::getValue)
.reduce(Integer::max)
.ifPresent(System.out::println)

System.out.println();
System.out.println("8. 전체 트랜잭션 중 최솟값은 얼마인가?");
transactions.stream()
.map(Transaction::getValue)
.reduce(Integer::min)
.ifPresent(System.out::println)
```

# 느낀점
- 하나의 flow로 흘러가는 로직에서는 유용하게 사용 될것같다.
- Stream 사용시 실행시간이 올라갔다. 따라서 Java언어에서는 Stream을 사용함에 따라 프로그램에 무리를 주지는 않는지 고려하고 사용해야 할것같다.
- 가독성이 좋아지는것 같다.
- 아래 링크를 통해 연습을 더 했으면 좋겠다.
  - https://intrepidgeeks.com/tutorial/java-stream-exercise
  - https://mangkyu.tistory.com/116
- 하나의 스트림내에 두개의 큰 로직을 사용하는것을 지양하자(할거면 새롭게 스트림을 만들어서 사용)
- 매핑을 한번 했으면 다시 풀 수 없으므로 필요한 만큼만 사용하자.
