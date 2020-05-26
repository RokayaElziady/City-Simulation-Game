package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location,
			int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		
		getTarget().getDisaster().setActive(false);
		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getToxicity() > 0) {
			target.setToxicity(target.getToxicity() - getTreatmentAmount());
			if (target.getToxicity() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getToxicity() == 0)
			heal();
		}
	

	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		 if(!compatible(r)) {
       	  throw new IncompatibleTargetException(this, r  ,"EL UNIT RAYHAA L TARGET GHALAT");
         }
         if(!canTreat(r)) {
       	  throw new CannotTreatException(this, r,"EL UNIT RAYHA L TARGET SAFE");
         }
         if(canTreat(r)&& compatible(r)) {
		if (getTarget() != null && ((Citizen) getTarget()).getToxicity() > 0
				&& getState() == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
         }
	}
	
	public String toString() {
		
		String m="";
		m+=" Unit Type: Disese Control Unit "+"\n";
		m+=super.toString(); 
		return  m;
		}
	public String getName() {
		return "Disease Control Unit";
	}


}
