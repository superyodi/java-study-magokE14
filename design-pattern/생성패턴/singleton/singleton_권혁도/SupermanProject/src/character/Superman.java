package character;

import java.util.LinkedList;
import java.util.Queue;

import character.civilian.CivilainState;
import character.civilian.Civilian;
import character.villain.Villain;

public class Superman {
	private static Superman superman = new Superman();
	Queue<Civilian> helpQueue = new LinkedList<>();
			
	private Superman() {}
	
	public static Superman getSuperman() {
		return superman;
	}
	
	public void called(Civilian civilian) {
		superman.helpQueue.add(civilian);
	}
	
	public void attackVillain() {
		Civilian civilian = helpQueue.peek();
		if(civilian.getState() == CivilainState.ATTACKED) {
			Villain villain = civilian.getVillain();
			
			int min = 10;
			int max = 20;
			int damage = (int)(Math.random() * (max - min)) + min;
			villain.attacked(damage);	
		}else {
			helpQueue.poll();
		}
	}
}
