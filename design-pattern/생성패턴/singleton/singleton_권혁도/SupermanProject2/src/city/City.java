package city;

import java.util.ArrayList;
import java.util.List;

import character.Superman;
import character.civilian.CivilainState;
import character.civilian.Civilian;
import character.villain.Villain;
import character.villain.VillainState;
import main.Main;

public class City {
	List<Civilian> civilianList = new ArrayList<>();
	List<Villain> villainList = new ArrayList<>();
	Superman superman;
	String winString;
	String loseString;
	
	public City(int civilianCount, int villainCount, String winString, String loseString) {
		for(int i = 0; i < civilianCount; i++) {
			civilianList.add(new Civilian());
		}
		for(int i = 0; i < villainCount; i++) {
			villainList.add(new Villain());
		}
		superman = Superman.getSuperman();
		this.winString = winString;
		this.loseString = loseString;
		//printCityStatus();
	}
	
	//public void run() {
		//new Thread(()->{
	public Thread run() {
		Thread thread = new Thread(()->{
			while(true) {
				// =================================================
				// 1. 승/패 판단
				// =================================================
				if(civilianList.size() > 0 && villainList.size() == 0) {
					if(this instanceof Seoul) {
						Main.atomicBoolean[0].set(true);
					}else if(this instanceof NewYork) {
						Main.atomicBoolean[1].set(true);
					}else if(this instanceof London) {
						Main.atomicBoolean[2].set(true);
					}
					System.out.println(winString);
					break;
				}
				else if(civilianList.size() == 0) {
					System.out.println(loseString);
					break;
				}
				
				// =================================================
				// 2. 악당이 시민을 위협함
				// =================================================
				// 위협하려는 악당 구하기
				for(Villain villain : villainList) {
					if(villain.getState() == VillainState.ALIVE) {
						for(Civilian civilian : civilianList) {
							if(civilian.getState() == CivilainState.ALIVE) {
								villain.setAttack(civilian);
								break;
							}
						}
						break;
					}
				}
					
				// =================================================
				// 3. 슈퍼맨이 시민을 도와줌
				// =================================================
				superman.attackVillain();
				
				// =================================================
				// 4. 악당이 시민을 공격함
				// =================================================
				for(Villain villain : villainList) {
					if(villain.getState() == VillainState.ATTACK) {
						villain.attack();
					}
				}
				
				// =================================================
				// 5. 시민들 현황 정리
				// =================================================
				civilianList.removeIf(civilian -> (civilian.getState() == CivilainState.DEAD));
				
				// =================================================
				// 6. 악당들 현황 정리
				// =================================================
				villainList.removeIf(villain -> (villain.getState() == VillainState.DEAD));
				

				// =================================================
				// 7. 도시 현황
				// =================================================
				//printCityStatus();
			}
		});
		thread.start();
		return thread;
	}
	
	private void printCityStatus() {
		System.out.println("==============================");
		System.out.println("시민 수: " + civilianList.size());
		System.out.println("악당 수: " + villainList.size());
		System.out.println("==============================");
	}
}
