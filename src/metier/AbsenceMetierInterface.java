package metier;

import java.util.List;

public interface AbsenceMetierInterface {
	public Groupe getGroupeFromId(Long id_groupe);
	public Matiere getMatiereFromId(Long id_matiere);
	public Horaire getHoraireFromId(Long id_horaire);
	public Seance getSeanceFromId(Long id_seance);
	public Eleve getEleveFromId(Long id_eleve);
	public Eleve getEleveFromIdd(Long id_eleve);
	public Enseignant getEnseignantFromId(Long id_enseignant);
	public Absence getAbsenceFromId(Long id_absence);
	
	public List<Groupe> listeGroupes(Enseignant e); //Retourne la liste des groupes qui ont cet enseignant
	public List<Seance> listeSeances(Groupe g, Enseignant e); //Retourne la liste des seances pour le groupe sélectionné avec cet enseignant
	public List<Eleve> listeEleves(Groupe g); //Retourne la liste des élèves du groupe sélectionné, pour cette séance
	
	public void faireAppel(Seance s, Enseignant e, Groupe g, List<Eleve> listeElevesAbsents); //Faire l'appel lors de la seance s
	public void confirmerAppel(List<Eleve> listeElevesAbsents, Seance s, Groupe g); //Affecte une absence à la séance aux élèves absents (appel ajouterAbsence pour chaque e)
	public void ajouterAbsence(Eleve e, Seance s, Groupe g); // Affecte une absence à l'élève e, pour la seance s
	
	public List<Absence> listeAbsences(Long numEleve); //Retourne la liste des abscences de l'eleve 
	public Enseignant getEnseignantFromLogin(String username, String password);
	public List<Seance> listeSeancesByEleve(Eleve e);
	public Eleve getEleveFromLogin(String username, String password);
	public List<Absence> allAbsences();
	
	public void ajouterjustif(Long id_absence);
	public void justifierAbsence(Long id_absence);
	public boolean isJustified(Long id_absence);
	
	public int nombreEtudiantByGroupe(Groupe g);
	List<Seance> listeSeancesByEnseigant(Enseignant e);
	
	
}
