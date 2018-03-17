package metier;

import java.util.Date;
import java.util.List;

public class Semestre {
	private long code;
	private Date dateDebut;
	private Date dateFin;
	private List<Horaire> listeHoraires;
	
	
	public Semestre(long code, Date dateDebut, Date dateFin, List<Horaire> listeHoraires) {
		super();
		this.code = code;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.listeHoraires = listeHoraires;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public List<Horaire> getListeHoraires() {
		return listeHoraires;
	}
	public void setListeHoraires(List<Horaire> listeHoraires) {
		this.listeHoraires = listeHoraires;
	}
	public Semestre() {
		super();
	}
	
	public void ajouterHoraire(Horaire h) {
		this.listeHoraires.add(h);
	}
	
	
	
	
	
	
}
