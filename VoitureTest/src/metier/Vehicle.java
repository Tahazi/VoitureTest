package metier;


/**
 * uio
 * @author Taha
 *
 */
public  class Vehicle {

	private int nbrDoors;
	private Carburant carburant;
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

	public Vehicle(int nbrDoors, Carburant carburant) {
		super();
		this.nbrDoors = nbrDoors;
		this.carburant = carburant;
	}

	public int getNbrDoors() {
		return nbrDoors;
	}

	public void setNbrDoors(int nbrDoors) {
		this.nbrDoors = nbrDoors;
	}

	public Carburant getCarburant() {
		return carburant;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}
	
	
}
