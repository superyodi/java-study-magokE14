package city;

import java.util.ArrayList;
import java.util.List;

import character.Superman;
import character.civilian.CivilainState;
import character.civilian.Civilian;
import character.villain.Villain;
import character.villain.VillainState;

public class Seoul{
	List<Civilian> civilianList = new ArrayList<>();
	List<Villain> villainList = new ArrayList<>();
	Superman superman;
	
	public Seoul(int civilianCount, int villainCount) {
		for(int i = 0; i < civilianCount; i++) {
			civilianList.add(new Civilian());
		}
		for(int i = 0; i < villainCount; i++) {
			villainList.add(new Villain());
		}
		superman = Superman.getSuperman();
		
		printCityStatus();
	}
	
	public void run() {
		while(true) {
			if(civilianList.size() > 0 && villainList.size() == 0) {
				System.out.println("==============================");
				System.out.println("슈퍼맨이 서울을 구했습니다.");
				System.out.println("==============================");
				break;
			}
			else if(civilianList.size() == 0 && villainList.size() > 0) {
				System.out.println("==============================");
				System.out.println("악당에 의해 서울에 혼란이 찾아왔습다.");
				System.out.println("==============================");
				break;
			}
			
			// =================================================
			// 악당이 시민을 위협함
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
			// 슈퍼맨이 시민을 도와줌
			// =================================================
			superman.attackVillain();
			
			// =================================================
			// 악당이 시민을 공격함
			// =================================================
			for(Villain villain : villainList) {
				if(villain.getState() == VillainState.ATTACK) {
					villain.attack();
				}
			}
			
			// =================================================
			// 시민들 현황 정리
			// =================================================
			civilianList.removeIf(civilian -> (civilian.getState() == CivilainState.DEAD));
			
			// =================================================
			// 악당들 현황 정리
			// =================================================
			villainList.removeIf(villain -> (villain.getState() == VillainState.DEAD));
			

			// =================================================
			// 도시 현황
			// =================================================
			printCityStatus();
		}
	}
	
	private void printCityStatus() {
		System.out.println("==============================");
		System.out.println("시민 수: " + civilianList.size());
		System.out.println("악당 수: " + villainList.size());
		System.out.println("==============================");
	}
}
