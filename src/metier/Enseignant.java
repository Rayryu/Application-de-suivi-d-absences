package metier;

import java.util.List;

public class Enseignant {

	
	private Long code;
	private String nom;
	private String prenom;
	private List<Groupe> listeGroupes;
	private List<Matiere> listeMatieres;
	public Enseignant(Long code, String nom, String prenom, List<Groupe> listeGroupes, List<Matiere> listeMatieres) {
		super();
		this.code = code;
		this.nom = nom;
		this.prenom = prenom;
		this.listeGroupes = listeGroupes;
		this.listeMatieres = listeMatieres;
	}
	
	public List<Seance> listeSeances(){
		AbsenceMetierInterface absc = new AbsenceMetierImpl();
		return absc.listeSeancesByEnseigant(this);
		
	}
	public Enseignant() {
		super();
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
	public List<Groupe> getListeGroupes() {
		return listeGroupes;
	}
	public void setListeGroupes(List<Groupe> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}
	public List<Matiere> getListeMatieres() {
		return listeMatieres;
	}
	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}
	
	
	

}
