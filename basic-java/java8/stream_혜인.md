# stream

### 1. 8진수 2진수

```java
import java.util.Scanner;
import java.util.stream.Collectors;

public class 팔진수이진수_1212 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
//        input.chars().forEach(ch -> String.format("%03d", Integer.toBinaryString(ch - '0')));
        
        String result = input.chars().mapToObj(c -> String.format("%03d", Integer.parseInt(Integer.toBinaryString((char) c - '0')))).collect(Collectors.joining());
  
        while(true){
            if(result.charAt(0) == '0' && result.length()>1)  result = result.substring(1, result.length());
            else break;
        }
        
        System.out.println(result);

	}

}
```

## 알파벳찾기

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 알파벳찾기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		int[] pos = new int[26];
		
		for(int i=0; i<pos.length;i++) {
			pos[i] = -1; 
		}
		
		char[] arr = sc.next().toCharArray();

		for (int i = 0; i < arr.length; i++) {
			if(pos[arr[i] - 'a'] < i && pos[arr[i] - 'a'] == -1) {
				pos[arr[i] - 'a'] = i;					
			}
			
		}
		
		for(int i : pos) {
			System.out.print(i+ " ");
		}
		
		*/
		
		
		Map <Character, Integer> num = new HashMap<>();
		String input = sc.next();
		input.chars().forEach(ch -> num.put((char) ch, input.indexOf((char) ch)));
	
		IntStream.range(0, 26).forEach( index  -> System.out.print(num.get((char) (index + 'a')) == null ? "-1 " : num.get((char) (index + 'a')) +" "));
		
		

	}

}
```

## 3. 복호화

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class 복호화 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		/*
		for(int i=0;i<N;i++) {
			String code = br.readLine();
			Map<Character, Integer> map = new HashMap<Character, Integer>(); 
			char[] charArray = code.toCharArray();
			for(char ch : charArray) {
				map.put(ch, map.getOrDefault(ch, 0) + 1);
			}
			
			int max = Integer.MIN_VALUE;
			char maxChar = ' ';
			for(char ch : map.keySet()) {
				if(max < map.get(ch)) {
					max = map.get(ch);
					maxChar = ch;
				}
			}
			
			int cnt = 0;
			for(char ch : map.keySet()) {
				if(max == map.get(ch)) {
					cnt++;
				}
			}
			System.out.println(cnt > 1 ? "?" : maxChar + "");
			
		}
		*/
		
		//*******************************************************************************************
		
		
		Map<Character, Integer> map = new HashMap<>();
		for(int i=0;i<N;i++) {
			String code = br.readLine();
			map = code.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c->1)));
			char maxChar = Collections.max(map.entrySet(), (e1, e2) -> e1.getValue() - e2.getValue()).getKey();

			
			final int val = map.get(maxChar);

			long isEqual = map.entrySet().stream().filter(a -> a.getValue().equals(val)).count();

			System.out.println(isEqual > 1 ? "?" : maxChar + "");
		}
		
	}

}
```