package character.villain;

import character.civilian.CivilainState;
import character.civilian.Civilian;

public class Villain{
	int hp;
	VillainState state;
	Civilian civilian;
	
	public Villain() {
		int min = 10;
		int max = 15;
		hp = (int)(Math.random() * (max - min)) + min;
		state = VillainState.ALIVE;
	}

	public void setState(VillainState state) {
		this.state = state;
	}

	public VillainState getState() {
		return state;
	}

	public void setAttack(Civilian civilian) {
		state = VillainState.ATTACK;
		this.civilian = civilian; 
		civilian.setAttacked(this);
	}
	
	public void attacked(int damage) {
		hp -= damage;
		if(hp <= 0) {
			state = VillainState.DEAD;
			civilian.setState(CivilainState.ALIVE);
			System.out.println("¾Ç´çÀÌ Á×À½");
		}
	}
	
	public void attack() {
		state = VillainState.ATTACK;
		int min = 5;
		int max = 10;
		int damage = (int)(Math.random() * (max - min)) + min;
		civilian.attacked(damage);
	}
}
