package metier;

public enum Carburant {
	Diesel(0.05,"Diesel"),
	Gasoline(0.06,"Gasoline"),
	Hybrid(0.03,"Hybrid");
	
	private Double per;
	private String libelle;
	
	private Carburant(Double per, String libelle) {
		this.per= per;
		this.libelle = libelle;
	}

	public Double getPer() {
		return per;
	}
	
	
	
	public String getLibelle() {
		return libelle;
	}

	public static Carburant getCarburant(String libelle) {
		for (Carburant carburant : Carburant.values()) {
			if (carburant.getLibelle().equals(libelle)) {
				return carburant;
			}
		}
		return null;
	}
}
