package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.people.Citizen;
import model.people.CitizenState;


public class Injury extends Disaster {

	public Injury(int startCycle, Citizen target) {
		super(startCycle, target);
	}
	@Override
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException 
	{
		Citizen target = (Citizen)getTarget();
		if(target.getState().equals(CitizenState.DECEASED)) {
			throw new CitizenAlreadyDeadException(this, "THE CITIZEN IS ALREADY DEAD 7ARAAAM 3ALEEK!!");
		}
		else {
		target.setBloodLoss(target.getBloodLoss()+30);
		super.strike();
		}
	}
	@Override
	public void cycleStep() {
		Citizen target = (Citizen)getTarget();
		target.setBloodLoss(target.getBloodLoss()+10);
		
	}
	
	public String toString() {
		String m ="Disaster Type: Injury"+"\n";
		m  += super.toString(); 
		return m;
	}
	public String getName() {
		return "Injury";
	}

}
