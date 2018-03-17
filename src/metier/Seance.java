package metier;

public class Seance {
	
	private Long code;
	private String nom;
	private Groupe groupe;
	private Matiere matiere;
	private Horaire horaire;
	private Enseignant enseignant;
	private boolean appel;
	
	public void infoSeance() {
		System.out.println(this.getNom() +" - " + this.getGroupe().getNom()+" - "+ this.getMatiere().getNom()+" - "+ this.getHoraire().getDate()+" - "+this.getHoraire().getHeureDebut()
				+" - "+ this.getHoraire().getHeureFin()+" - "+ this.getEnseignant().getNom());
		
	}
	
	public String infos(){
		return " " + this.getNom() + " / Séance du " + _infos();	}
	
	public String _infos() {
		return this.getHoraire().getDate() + " ("+this.getHoraire().getHeureDebut()+"-"+this.getHoraire().getHeureFin()+")";
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
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public Horaire getHoraire() {
		return horaire;
	}
	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public boolean isAppel() {
		return appel;
	}
	public void setAppel(boolean appel) {
		this.appel = appel;
	}
	public Seance(Long code, String nom, Groupe groupe, Matiere matiere, Horaire horaire, Enseignant enseignant,
			boolean appel) {
		super();
		this.code = code;
		this.nom = nom;
		this.groupe = groupe;
		this.matiere = matiere;
		this.horaire = horaire;
		this.enseignant = enseignant;
		this.appel = appel;
	}
	public Seance() {
		super();
	}
	
	
	
	
}
