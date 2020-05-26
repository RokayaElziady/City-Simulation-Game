package model.disasters;

import javax.management.InstanceAlreadyExistsException;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	public Disaster(int startCycle, Rescuable target) {
		this.startCycle = startCycle;
		this.target = target;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getStartCycle() {
		return startCycle;
	}
	public Rescuable getTarget() {
		return target;
	}
	public void strike() throws CitizenAlreadyDeadException ,BuildingAlreadyCollapsedException
	{
		
		target.struckBy(this);
		active=true;
	}
	public String toString() {
		String m  = "StartCycle "+startCycle+"\n"; 
		if( this instanceof Injury || this instanceof Infection )
		m += "Target: Simpson With NationalID "+ ((Citizen)target).getNationalID()+"\n"; 
		else {
			m+="Target: An Aztec At "+((ResidentialBuilding)target).getLocation().toString();
		}
		return m;
	}
	public  String getName() {
		return "";	}
		
   
	
}
