package util;

import java.util.ArrayList;
import java.util.List;
import metier.Carburant;
import metier.Motorcycle;
import metier.Vehicle;

public class Util {

	/**
	 * fggggg
	 * 
	 * @param code
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private static Vehicle getSpecialClasseName(String code)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		Class<Vehicle> classe = (Class<Vehicle>) Class.forName("metier."+adaptName(code));

		return classe.newInstance();
	}

	
	/**
	 * 
	 * @param code
	 * @return
	 */
	private static int getNbrOfDoorsFromString(String code) {
		return code.split(" ").length;
	}

	/*private static Carburant getCarburantFromString(String code) {

		return Carburant.getCarburant(code);
	}*/

	/**
	 * 
	 * @param vehicles
	 * @param name
	 * @param doorsClosed
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static boolean isDoorsOk(List<Vehicle> vehicles, String name, String doorsClosed)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		Vehicle vehicle = getVehicleFromList(vehicles, name);
		if(vehicle instanceof Motorcycle) {
			return true;
		}
		if (vehicle.getNbrDoors() == getNbrOfDoorsFromString(doorsClosed))
			return true;
		return false;

	}

	/**
	 * 
	 * @param vehicles
	 * @param name
	 * @return
	 */
	private static Vehicle getVehicleFromList(List<Vehicle> vehicles, String name) {
		for (Vehicle v : vehicles) {
			if (v.getClass().getSimpleName().equals(adaptName(name)))
				return v;
		}
		return null;

	}
	
	/*
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}*/

	/**
	 * 
	 * @param vehicles
	 * @param name
	 * @param km
	 * @return
	 */
	public static String calculeConsommation(List<Vehicle> vehicles,String name, String km) {
		
		String K =km.replace(" KM", "");
		
		System.out.println(String.format("%.2f", Double.parseDouble(K) * 
		getVehicleFromList(vehicles,name).getCarburant().getPer()));
		
		return String.format("%.2f", Double.parseDouble(K) * getVehicleFromList(vehicles,name).getCarburant().getPer()).replace(',', '.');
		
		//return new DecimalFormat("#.00").format(Double.parseDouble(K) * getVehicleFromList(vehicles,name).getCarburant().getPer());

	}

	/**
	 * 
	 * @param vehicle
	 * @return
	 */
	private static List<Integer> getListOfDoors(Vehicle vehicle) {
		List<Integer> resultat = new ArrayList<Integer>();
		for (int i = 0; i < vehicle.getNbrDoors(); i++) {
			resultat.add(i + 1);
		}
		return resultat;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	private static List<Integer> getClosedDoorsList(String code) {
		String[] params = code.split(" ");
		List<Integer> list = new ArrayList<Integer>();

		for (String string : params) {
			list.add(Integer.parseInt(string));
		}
		return list;

	}
	
	
	/**
	 * 
	 * @param vehicle
	 * @param code
	 * @return
	 */
	private static List<Integer> getOpenDoors(Vehicle vehicle, String code) {

		List<Integer> listOfClosedDoors = getClosedDoorsList(code);
		List<Integer> listOfDoors = getListOfDoors(vehicle);
		List<Integer> listOfOpenDoors = new ArrayList<Integer>();
		
		for (Integer door : listOfDoors) {
			if (!listOfClosedDoors.contains(door)) {
				listOfOpenDoors.add(door);
			}
		}
		return listOfOpenDoors;

	}
	
	
	/**
	 * 
	 * @param door
	 * @param state
	 * @return
	 */
	private static String printSymbolOfDoor(int door, boolean state) {
		
		if (state) 
			return printClosedDoorSymbol(door);
		return printOpenDoorSymbol(door);
	}

	
	/**
	 * 
	 * @param nbr
	 * @return
	 */
	private static boolean getParite(int nbr) {
		
		if(nbr%2 == 0)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param door
	 * @return
	 */
	private static String printOpenDoorSymbol(int door) {
		if (getParite(door)) 
			return " \\\n";
		return " /";
	}
	
	/**
	 * 
	 * @param door
	 * @return
	 */
	private static String printClosedDoorSymbol(int door) {
		if (getParite(door)) 
			return " |\n";
		return " |";
	}
	
	
	/**
	 * 
	 * @param vehicles
	 * @param name
	 * @param code
	 * @return
	 */
	public static StringBuffer printVehicle(List<Vehicle> vehicles,String name, String code) {
		StringBuffer resultat = new StringBuffer();
		
		resultat.append("  _\n");
		
		for (Integer door : getListOfDoors(getVehicleFromList(vehicles, name))) {
			if (getOpenDoors(getVehicleFromList(vehicles, name), code).contains(door)) {
				resultat.append(printSymbolOfDoor(door, false));
			
			}else {
				resultat.append(printSymbolOfDoor(door, true));	
			}
		}
		
		resultat.deleteCharAt(resultat.length()-3);
		resultat.insert(resultat.length()-2, "_");
		resultat.deleteCharAt(resultat.length()-1);
		
		System.out.println(resultat);
		
		return resultat;
	}
	
	
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static Vehicle getVehicleFromString(String code)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Vehicle vehicle = null;

		String[] params = code.split(":");

		vehicle = getSpecialClasseName(params[0]);

		vehicle.setCarburant(Carburant.getCarburant(params[1]));
		vehicle.setNbrDoors(Integer.parseInt(params[2]));
		

		return vehicle;
	}

	/**
	 * 
	 * @param code
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static List<Vehicle> getVehiclesFromString(String code)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String c;
		List<Vehicle> listeVehicules = new ArrayList<>();
		c=code.replace(" ", "");
		
		String[] params = c.split(",");

		for (String vehicule : params) {
			
			listeVehicules.add(getVehicleFromString(vehicule));
		}

		return listeVehicules;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	private static String adaptName(String str) {
		String s;
		s = str.replace(str.substring(1), str.substring(1).toLowerCase());
		return s;

	}
}
