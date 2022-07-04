package city;

import java.util.ArrayList;
import java.util.List;

import character.Superman;
import character.civilian.CivilainState;
import character.civilian.Civilian;
import character.villain.Villain;
import character.villain.VillainState;

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
	
	public void run() {
		new Thread(()->{
			while(true) {
				// =================================================
				// 1. ½Â/ÆÐ ÆÇ´Ü
				// =================================================
//				boolean isAllDeadCivilian = true;
//				boolean isAllDeadVillain = true;
//				for(Civilian civilian : civilianList) {
//					if(civilian.getState() != CivilainState.DEAD) {
//						isAllDeadCivilian = false;
//						break;
//					}
//				}
//				for(Villain villain : villainList) {
//					if(villain.getState() != VillainState.DEAD) {
//						isAllDeadVillain = false;
//						break;
//					}
//				}
//				
//				if(!isAllDeadCivilian && isAllDeadVillain) {
//					System.out.println(winString);
//					break;
//				}
//				else if(isAllDeadCivilian) {
//					System.out.println(loseString);
//					break;
//				}
				
				if(civilianList.size() > 0 && villainList.size() == 0) {
					System.out.println(winString);
					break;
				}
				else if(civilianList.size() == 0 && villainList.size() > 0) {
					System.out.println(loseString);
					break;
				}
				
				// =================================================
				// 2. ¾Ç´çÀÌ ½Ã¹ÎÀ» À§ÇùÇÔ
				// =================================================
				// À§ÇùÇÏ·Á´Â ¾Ç´ç ±¸ÇÏ±â
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
				// 3. ½´ÆÛ¸ÇÀÌ ½Ã¹ÎÀ» µµ¿ÍÁÜ
				// =================================================
				superman.attackVillain();
				
				// =================================================
				// 4. ¾Ç´çÀÌ ½Ã¹ÎÀ» °ø°ÝÇÔ
				// =================================================
				for(Villain villain : villainList) {
					if(villain.getState() == VillainState.ATTACK) {
						villain.attack();
					}
				}
				
				// =================================================
				// 5. ½Ã¹Îµé ÇöÈ² Á¤¸®
				// =================================================
				civilianList.removeIf(civilian -> (civilian.getState() == CivilainState.DEAD));
				
				// =================================================
				// 6. ¾Ç´çµé ÇöÈ² Á¤¸®
				// =================================================
				villainList.removeIf(villain -> (villain.getState() == VillainState.DEAD));
				

				// =================================================
				// 7. µµ½Ã ÇöÈ²
				// =================================================
				//printCityStatus();
			}
		}).start();
	}
	
	private void printCityStatus() {
		System.out.println("==============================");
		System.out.println("½Ã¹Î ¼ö: " + civilianList.size());
		System.out.println("¾Ç´ç ¼ö: " + villainList.size());
		System.out.println("==============================");
	}
}
