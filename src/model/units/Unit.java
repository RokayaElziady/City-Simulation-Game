package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;
	private int res=0;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r) throws IncompatibleTargetException , CannotTreatException {
          if(!compatible(r)) {
        	  throw new IncompatibleTargetException(this, r ,"EL UNIT RAYHAA L TARGET GHALAT");
          }
          if(!canTreat(r)) {
        	  throw new CannotTreatException(this, r,"EL UNIT RAYHA L TARGET SAFE");
          }
          if(canTreat(r)&& compatible(r)) {
		if (target != null && state == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
          }

	}

	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r){
//		if(!compatible(r)) {
//			throw new IncompatibleTargetException(this, r);
//		}
//		 if(!canTreat(r)) {
//       	  throw new CannotTreatException(this, r);
//         }
//		 if(compatible( r)&& canTreat(r)) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());
		 }

//	}

	public abstract void treat() ;

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
				treat();
			
			
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;
	}
public boolean compatible(Rescuable r) {
	if(r instanceof ResidentialBuilding) {
		if(this instanceof FireTruck || this instanceof GasControlUnit || this instanceof Evacuator)
			return true;
	}
	if( r instanceof Citizen) {
		if(this instanceof Ambulance || this instanceof DiseaseControlUnit)
			return true;
	}
	return false;
	
}
	
	public boolean canTreat(Rescuable r) {
		if(r instanceof ResidentialBuilding) {
			if(((ResidentialBuilding)r).getFireDamage()!=0 && this instanceof FireTruck)
				return true;
			if(((ResidentialBuilding)r).getGasLevel()!=0 && this instanceof GasControlUnit)
				return true;
			if(((ResidentialBuilding)r).getFoundationDamage()!=0 && this instanceof Evacuator)
				return true;
			
		}
		else if( r instanceof Citizen) {
			if(((Citizen)r).getBloodLoss()!=0 && this instanceof Ambulance)
				  return true;
			if(((Citizen)r).getToxicity()!=0 && this instanceof DiseaseControlUnit)
				  return true;
		}
		return false;
	}
	
	public String toString() {
		String m ="";
		m+= "UnitID:  " + unitID +"  \n" ;
		m+= "location:  " +"" + location.toString() +"  \n";
		if(target instanceof Citizen)
		    m+= "  target : Citizen  ";
		else {
			m+="target : Building  ";
		}
		if(target !=null)
		m+= "LocationOfTarget : "+ target.getLocation().toString()+"  \n";
		m+= "Steps Per Cycle:  "+this.stepsPerCycle+"  \n";
		m+= "Unit State:  "+this.state+"  \n  ";
		
		return m;
	}
	
	public String getName() {
		return "";
	}

}
