package metier;

import java.util.List;

public class Groupe {
	
	public Groupe(Long num, String nom, List<Eleve> listeEleves, List<Enseignant> listeEnseignants) {
		super();
		this.num = num;
		this.nom = nom;
		this.listeEleves = listeEleves;
		this.listeEnseignants = listeEnseignants;
	}
	
	
	
	public int getNombreEtudiants() {
		AbsenceMetierInterface absc = new AbsenceMetierImpl();
		return absc.nombreEtudiantByGroupe(this);
	}
	
	public Groupe() {
		super();
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Eleve> getListeEleves() {
		return listeEleves;
	}
	public void setListeEleves(List<Eleve> listeEleves) {
		this.listeEleves = listeEleves;
	}
	public List<Enseignant> getListeEnseignants() {
		return listeEnseignants;
	}
	public void setListeEnseignants(List<Enseignant> listeEnseignants) {
		this.listeEnseignants = listeEnseignants;
	}
	private Long num;
	private String nom;
	private List<Eleve> listeEleves;
	private List<Enseignant> listeEnseignants;
	
}
