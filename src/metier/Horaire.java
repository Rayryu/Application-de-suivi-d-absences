package metier;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Horaire {
	private long code;
	private Date date;
	private Time heureDebut;
	private Time heureFin;
	private List<Seance> listeSeances;
	public Horaire(long code, Date date, Time heureDebut, Time heureFin, List<Seance> listeSeances) {
		super();
		this.code = code;
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.listeSeances = listeSeances;
	}
	
	public String getFullHoraire() {
		return this.getDate() + " (" + this.getHeureDebut() + "-" + this.getHeureFin() +")";
	}
	public Horaire() {
		super();
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Time getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	public List<Seance> getListeSeances() {
		return listeSeances;
	}
	public void setListeSeances(List<Seance> listeSeances) {
		this.listeSeances = listeSeances;
	}

	
	
	
	
	
	
}
