package character;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import character.civilian.CivilainState;
import character.civilian.Civilian;
import character.villain.Villain;
import character.villain.VillainState;

public class Superman {
//	private Queue<Civilian> helpQueue = new LinkedList<>();
	private LinkedBlockingQueue<Civilian> helpQueue = new LinkedBlockingQueue<>();
	private AtomicBoolean lock = new AtomicBoolean(false);
			
	private Superman() {}
	
	public static Superman getSuperman() {
		return LasyHolder.superman;
	}
	
	private static class LasyHolder{
		public static final Superman superman = new Superman();
	}
	
//	public synchronized void called(Civilian civilian) {
	public void called(Civilian civilian) {
		Superman superman = getSuperman();
		superman.helpQueue.add(civilian);
	}
	
//	public synchronized void attackVillain() {
	public void attackVillain() {
		if(lock.get())
			return;
		lock.set(true);
		
		Civilian civilian = null;
		while(helpQueue.size() > 0) {
			if(helpQueue.peek().getState() != CivilainState.ATTACKED) {
				helpQueue.poll();
			}else {
				civilian = helpQueue.peek();
				break;
			}
		}
		
		if(civilian != null) {
			Villain villain = civilian.getVillain();
			
			int min = 10;
			int max = 20;
			int damage = (int)(Math.random() * (max - min)) + min;
			if(villain.attacked(damage) == VillainState.DEAD) {//¾Ç´çÀÌ Á×À» °æ¿ì
				helpQueue.poll();
			}
		}

		lock.set(false);
	}
}
