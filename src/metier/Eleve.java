package metier;

import java.util.List;

public class Eleve {
	private Long id;
	private Long code;
	private String nom;
	private String prenom;
	private List<Absence> listeAbsences;
	
	public List<Seance> listeSeances(){
		AbsenceMetierInterface m = new AbsenceMetierImpl();
		return m.listeSeancesByEleve(this);
		
	}
	
	public void infoEleve() {
		System.out.println(this.getNom() + " - " + this.getPrenom()+ " - " + this.getCode());
	}
	
	public Eleve() {
		super();
	}
	public Eleve(Long code, String nom, String prenom, List<Absence> listeAbsences) {
		super();
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.listeAbsences = listeAbsences;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public List<Absence> getListeAbsences() {
		return listeAbsences;
	}
	public void setListeAbsences(List<Absence> listeAbsences) {
		this.listeAbsences = listeAbsences;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
