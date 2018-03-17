package metier;

public class Matiere {

	public Matiere() {
		super();
	}
	public Matiere(Long code, String nom, String desc) {
		super();
		this.code = code;
		this.nom = nom;
		this.desc = desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private Long code;
	private String nom;
	private String desc;
	

}
