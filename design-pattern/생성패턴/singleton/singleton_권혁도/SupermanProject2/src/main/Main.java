package main;

import java.util.concurrent.atomic.AtomicBoolean;

import city.London;
import city.NewYork;
import city.Seoul;

public class Main {
	private static final int CITY_CNT = 3;
	public static AtomicBoolean[] atomicBoolean = new AtomicBoolean[CITY_CNT];
	
	public static void main(String[] args) {
		int winCnt = 0;
		Thread[] cityThread = new Thread[CITY_CNT];
		for(int i = 0; i < CITY_CNT; i++) {
			atomicBoolean[i] = new AtomicBoolean(false);
		}
		
		for(int x = 0; x < 100; x++) {
			for(int i = 0; i < CITY_CNT; i++) {
				atomicBoolean[i].set(false);
			}
			// =================================================
			// 서울
			// =================================================
			StringBuffer winSb = new StringBuffer();
			StringBuffer loseSb = new StringBuffer();
			int civilianCount = 2000;
			int villainCount = 10;
			winSb.append("==============================\n");
			winSb.append("슈퍼맨이 서울을 구했습니다.\n");
			winSb.append("==============================\n");
			loseSb.append("==============================\n");
			loseSb.append("악당에 의해 서울에 혼란이 찾아왔습다.\n");
			loseSb.append("==============================\n");
			Seoul seoul = new Seoul(civilianCount, villainCount, winSb.toString(), loseSb.toString());
			cityThread[0] = seoul.run();
			
			// =================================================
			// 뉴욕
			// =================================================
			winSb = new StringBuffer();
			loseSb = new StringBuffer();
			civilianCount = 2000;
			villainCount = 10;
			winSb.append("==============================\n");
			winSb.append("슈퍼맨이 뉴욕을 구했습니다.\n");
			winSb.append("==============================\n");
			loseSb.append("==============================\n");
			loseSb.append("악당에 의해 뉴욕이 혼란이 찾아왔습다.\n");
			loseSb.append("==============================\n");
			NewYork newYork = new NewYork(civilianCount, villainCount, winSb.toString(), loseSb.toString());
			cityThread[1] = newYork.run();
			
			
			// =================================================
			// 런던
			// =================================================
			winSb = new StringBuffer();
			loseSb = new StringBuffer();
			civilianCount = 2000;
			villainCount = 10;
			winSb.append("==============================\n");
			winSb.append("슈퍼맨이 런던을 구했습니다.\n");
			winSb.append("==============================\n");
			loseSb.append("==============================\n");
			loseSb.append("악당에 의해 런던에 혼란이 찾아왔습다.\n");
			loseSb.append("==============================\n");
			London london = new London(civilianCount, villainCount, winSb.toString(), loseSb.toString());
			cityThread[2] = london.run();
			
			while(!(cityThread[0].getState() == Thread.State.TERMINATED &&
					cityThread[1].getState() == Thread.State.TERMINATED &&
					cityThread[2].getState() == Thread.State.TERMINATED ));
			if(atomicBoolean[0].get() && atomicBoolean[1].get() && atomicBoolean[2].get()) {
				winCnt++;
			}
		}
		System.out.println("슈퍼맨이 세상을 구한 수: " + winCnt);
	}
}
