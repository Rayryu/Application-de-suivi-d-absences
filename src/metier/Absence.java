package metier;

public class Absence {

	private Long code;
	private Eleve eleve;
	private Groupe groupe;
	private Seance seance;
	private int justifiee;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Eleve getEleve() {
		return eleve;
	}
	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
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
	public Absence(Long code, Eleve eleve, Groupe groupe, Seance seance) {
		super();
		this.code = code;
		this.eleve = eleve;
		this.groupe = groupe;
		this.seance = seance;
	}
	public Absence() {
		super();
	}
	public int getJustifiee() {
		return justifiee;
	}
	public void setJustifiee(int justifiee) {
		this.justifiee = justifiee;
	}
	
	
	
}
