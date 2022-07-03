package character.civilian;

import character.Superman;
import character.villain.Villain;
import character.villain.VillainState;

public class Civilian {
	private int hp;
	private CivilainState state;
	private Villain villain;
	
	public Civilian() {
		int min = 10;
		int max = 15;
		hp = (int)(Math.random() * (max - min)) + min;
		state = CivilainState.ALIVE;
	}
	
	public void setState(CivilainState state) {
		this.state = state;
	}

	public CivilainState getState() {
		return state;
	}

	public Villain getVillain() {
		return villain;
	}

	public void help() {
		System.out.println("µµøÕ¡‡ø‰ Ω¥∆€∏«!");
	}
	
	public void setAttacked(Villain villain) {
		state = CivilainState.ATTACKED;
		this.villain = villain; 
		Superman superman = Superman.getSuperman();
		superman.called(this);
		System.out.println("µµøÕ¡‡ø‰ Ω¥∆€∏«");
	}
	
	public void attacked(int damage) {
		hp -= damage;
		if(hp < damage) {
			state = CivilainState.DEAD;
			villain.setState(VillainState.ALIVE);
			System.out.println("Ω√πŒ¿Ã ¡◊¿Ω");
		}
	}
}
