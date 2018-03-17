package web;

import java.util.ArrayList;
import java.util.List;

import metier.Absence;
import metier.Eleve;
import metier.Enseignant;
import metier.Groupe;
import metier.Seance;

public class AbsenceModel {
	private Long id_enseigant;
	private Long id_groupe;
	private Groupe groupe;
	private Seance seance;
	private Absence absence;
	private Enseignant enseignant;
	private List<Groupe> listeGroupes = new ArrayList<>();
	private List<Seance> listeSeances = new ArrayList<>();
	private List<Eleve> listeEleves = new ArrayList<>();
	private List<Eleve> listeElevesAbsents = new ArrayList<>();
	
	public Long getId_enseigant() {
		return id_enseigant;
	}
	public void setId_enseigant(Long id_enseigant) {
		this.id_enseigant = id_enseigant;
	}
	public Long getId_groupe() {
		return id_groupe;
	}
	public void setId_groupe(Long id_groupe) {
		this.id_groupe = id_groupe;
	}
	public List<Groupe> getListeGroupes() {
		return listeGroupes;
	}
	public void setListeGroupes(List<Groupe> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}
	public List<Seance> getListeSeances() {
		return listeSeances;
	}
	public void setListeSeances(List<Seance> listeSeances) {
		this.listeSeances = listeSeances;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Seance getSeance() {
		return seance;
	}
	public void setSeance(Seance seance) {
		this.seance = seance;
	}
	public List<Eleve> getListeEleves() {
		return listeEleves;
	}
	public void setListeEleves(List<Eleve> listeEleves) {
		this.listeEleves = listeEleves;
	}
	public List<Eleve> getListeElevesAbsents() {
		return listeElevesAbsents;
	}
	public void setListeElevesAbsents(List<Eleve> listeElevesAbsents) {
		this.listeElevesAbsents = listeElevesAbsents;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Absence getAbsence() {
		return absence;
	}
	public void setAbsence(Absence absence) {
		this.absence = absence;
	}

	
}
