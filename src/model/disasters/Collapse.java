package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;


public class Collapse extends Disaster {

	public Collapse(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);
		
	}
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
	{
		ResidentialBuilding target= (ResidentialBuilding)getTarget();	
		if(target.getStructuralIntegrity()==0) {
			throw new BuildingAlreadyCollapsedException(this,"EL BUILDING KHALAS ETHAD");
		}
		else {
		target.setFoundationDamage(target.getFoundationDamage()+10);
		super.strike();
	}
	}
	
	public void cycleStep()
	{
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setFoundationDamage(target.getFoundationDamage()+10);
	}
	public String toString() {
		String m  = "Disaster Type: Collapse "+"\n";
		m+=super.toString(); 
		return m;
	}
	public String getName() {
		return "Collapse";
	}
}
