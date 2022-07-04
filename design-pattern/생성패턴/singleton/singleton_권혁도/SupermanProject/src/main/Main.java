package main;

import city.London;
import city.NewYork;
import city.Seoul;

public class Main {
	public static void main(String[] args) {
		// =================================================
		// 서울
		// =================================================
		StringBuffer winSb = new StringBuffer();
		StringBuffer loseSb = new StringBuffer();
		int civilianCount = 20000;
		int villainCount = 100;
		winSb.append("==============================\n");
		winSb.append("슈퍼맨이 서울을 구했습니다.\n");
		winSb.append("==============================\n");
		loseSb.append("==============================\n");
		loseSb.append("악당에 의해 서울에 혼란이 찾아왔습다.\n");
		loseSb.append("==============================\n");
		Seoul seoul = new Seoul(civilianCount, villainCount, winSb.toString(), loseSb.toString());
		seoul.run();
		
		
		// =================================================
		// 뉴욕
		// =================================================
		winSb = new StringBuffer();
		loseSb = new StringBuffer();
		civilianCount = 20000;
		villainCount = 100;
		winSb.append("==============================\n");
		winSb.append("슈퍼맨이 뉴욕을 구했습니다.\n");
		winSb.append("==============================\n");
		loseSb.append("==============================\n");
		loseSb.append("악당에 의해 뉴욕이 혼란이 찾아왔습다.\n");
		loseSb.append("==============================\n");
		NewYork newYork = new NewYork(civilianCount, villainCount, winSb.toString(), loseSb.toString());
		newYork.run();
		
		
		// =================================================
		// 런던
		// =================================================
		winSb = new StringBuffer();
		loseSb = new StringBuffer();
		civilianCount = 20000;
		villainCount = 100;
		winSb.append("==============================\n");
		winSb.append("슈퍼맨이 런던을 구했습니다.\n");
		winSb.append("==============================\n");
		loseSb.append("==============================\n");
		loseSb.append("악당에 의해 런던에 혼란이 찾아왔습다.\n");
		loseSb.append("==============================\n");
		London london = new London(civilianCount, villainCount, winSb.toString(), loseSb.toString());
		london.run();
		
	}
}
