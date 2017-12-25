package metier;

import java.util.List;

import util.Util;

public class Vehicles {

	private List<Vehicle> vehicles;

	public Vehicles() {
		// TODO Auto-generated constructor stub
	}

	public Vehicles(String code)  {
		super();
		try {
			this.vehicles = Util.getVehiclesFromString(code);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String move(String name, String doorsClosed, String km)  {
		
		
		try {
			if (Util.isDoorsOk(vehicles, name, doorsClosed)) {
				return "DOORS OK, MOVING. The "+name+" will consume "+
			Util.calculeConsommation(vehicles, name, km)+" L";
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "DOORS KO, BLOCKED \n" + Util.printVehicle(vehicles, name, doorsClosed).toString();
		
	}
	
	
}
