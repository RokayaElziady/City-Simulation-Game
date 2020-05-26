package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class Fire extends Disaster {

	public Fire(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);
		
	}
	@Override
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException
	{
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		if(target.getStructuralIntegrity()==0) {
			throw new BuildingAlreadyCollapsedException(this,"EL BUILDING KHALAS ETHAD");
		}
		else {
		target.setFireDamage(target.getFireDamage()+10);
		super.strike();
		}
}

	@Override
	public void cycleStep() {
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFireDamage(target.getFireDamage()+10);
		
	}
	
	public String toString() {
		String m=  "Disaster Type: Fire"+"\n"; 
		m+= super.toString();
		return m;
	}
	public String getName() {
		return "Fire";
	}

}
