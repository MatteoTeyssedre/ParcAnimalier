package application;

public class Animal {
	
	private String famille;
	private String nom;
	private String photo;
	private String race;
	private String age;
	private String sexe;
	private String taille;
	private String coordRefuge;
	
	public Animal(String famille, String nom, String photo, String race, String age, String sexe, String taille,
			String coordRefuge) {
		this.famille = famille;
		this.nom = nom;
		this.photo = photo;
		this.race = race;
		this.age = age;
		this.sexe = sexe;
		this.taille = taille;
		this.coordRefuge = coordRefuge;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getCoordRefuge() {
		return coordRefuge;
	}

	public void setCoordRefuge(String coordRefuge) {
		this.coordRefuge = coordRefuge;
	}
}
