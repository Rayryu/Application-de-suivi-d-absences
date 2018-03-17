package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class AbsenceMetierImpl implements AbsenceMetierInterface{
	
	public Groupe getGroupeFromId(Long id_groupe) {
		Groupe g = new Groupe();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from groupe where id_groupe=?;");
			ps.setLong(1, id_groupe);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				g.setNum(rs.getLong("Id_groupe"));
				g.setNom(rs.getString("Nom_groupe"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Aucun groupe avec cet id : " + id_groupe);
			e.printStackTrace();
		}
		return g;
	}
	
	@Override
	public Matiere getMatiereFromId(Long id_matiere) {
		Matiere m = new Matiere();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from matiere where id_matiere=?;");
			ps.setLong(1, id_matiere);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				m.setCode(id_matiere);
				m.setNom(rs.getString("nom_matiere"));
				m.setDesc(rs.getString("desc_matiere"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Aucune matiere avec cet id : " + id_matiere);
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Horaire getHoraireFromId(Long id_horaire) {
		Horaire h = new Horaire();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from horaire where id_horaire=?;");
			ps.setLong(1, id_horaire);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				h.setCode(id_horaire);
				h.setDate(rs.getDate("date_horaire"));
				h.setHeureDebut(rs.getTime("heure_debut"));
				h.setHeureFin(rs.getTime("heure_fin"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Aucun horaire avec cet id : " + id_horaire);
			e.printStackTrace();
		}
		return h;
	}

	@Override
	public Seance getSeanceFromId(Long id_seance) {
		Seance s = new Seance();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from seance where id_seance=?;");
			ps.setLong(1, id_seance);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s.setCode(id_seance);
				s.setAppel(rs.getBoolean("appel"));
				s.setGroupe(getGroupeFromId(rs.getLong("id_groupe")));
				s.setHoraire(getHoraireFromId(rs.getLong("id_horaire")));
				s.setMatiere(getMatiereFromId(rs.getLong("id_matiere")));
				s.setNom(rs.getString("nom_seance"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Aucune seance avec cet id : " + id_seance);
			e.printStackTrace();
		}
		return s;
	}
	

	@Override	
	public Eleve getEleveFromId(Long num_eleve) {
		Eleve e= new Eleve();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from eleve where num_eleve=?;");
			ps.setLong(1, num_eleve);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.setId(rs.getLong("id_eleve"));
				e.setCode(num_eleve);
				e.setNom(rs.getString("nom_eleve"));
				e.setPrenom(rs.getString("prenom_eleve"));
			}
			ps.close();
		} catch (SQLException ex) {
			System.out.println("Aucun eleve avec cet id : " + num_eleve);
			ex.printStackTrace();
		}
		return e;
	}
	
	@Override
	public Eleve getEleveFromIdd(Long num_eleve) {
		Eleve e= new Eleve();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from eleve where id_eleve=?;");
			ps.setLong(1, num_eleve);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.setId(rs.getLong("id_eleve"));
				e.setCode(rs.getLong("num_eleve"));
				e.setNom(rs.getString("nom_eleve"));
				e.setPrenom(rs.getString("prenom_eleve"));
			}
			ps.close();
		} catch (SQLException ex) {
			System.out.println("Aucun eleve avec cet id : " + num_eleve);
			ex.printStackTrace();
		}
		return e;
	}

	
	@Override
	public List<Groupe> listeGroupes(Enseignant e) {
		List<Groupe> listeGroupes = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select groupe.id_groupe from ens_groupe, groupe where ens_groupe.id_groupe= groupe.id_groupe and Id_enseignant=? order by Nom_groupe;");
			ps.setLong(1, e.getCode());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Groupe g = new Groupe();
				g = getGroupeFromId(rs.getLong("id_groupe"));
				listeGroupes.add(g);
				
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucun groupe pour l'enseignant choisi!");
			e1.printStackTrace();
		}
		return listeGroupes;
	}

	@Override
	public List<Seance> listeSeances(Groupe g, Enseignant e) {
		List<Seance> listeSeances = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id_seance FROM `seance` WHERE Id_enseignant=? and Id_groupe=?;");
			ps.setLong(1, e.getCode());
			ps.setLong(2, g.getNum());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Seance s = new Seance();
				s = getSeanceFromId(rs.getLong("id_seance"));
				s.setEnseignant(e);
				listeSeances.add(s);
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucune liste de seance pour ce groupe : "+ g.getNom());
			e1.printStackTrace();
		}
		Collections.sort(listeSeances, (p1, p2) ->  p1.getHoraire().getDate().compareTo(p2.getHoraire().getDate()));
		return listeSeances;
	}
	

	@Override
	public List<Seance> listeSeancesByEleve(Eleve e) {
		List<Seance> listeSeances = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT seance.Id_Seance, Id_horaire, Id_matiere, Id_enseignant, seance.Id_groupe, appel, Nom_seance\r\n" + 
					"FROM  `seance` INNER JOIN groupe_eleve ON seance.Id_groupe = groupe_eleve.Id_groupe where groupe_eleve.Id_eleve = ?;");
			ps.setLong(1, e.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Seance s = new Seance();
				s = getSeanceFromId(rs.getLong("id_seance"));
				s.setEnseignant(getEnseignantFromId(rs.getLong("id_enseignant")));
				listeSeances.add(s);
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucune liste de seance pour cet élève : "+ e.getNom());
			e1.printStackTrace();
		}
		Collections.sort(listeSeances, (p1, p2) ->  p1.getHoraire().getDate().compareTo(p2.getHoraire().getDate()));
		return listeSeances;
	}

	@Override
	public List<Eleve> listeEleves(Groupe g) {
		List<Eleve> listeEleves = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select num_eleve from eleve, groupe_eleve where groupe_eleve.id_eleve= eleve.id_eleve and Id_groupe=?;");
			ps.setLong(1, g.getNum());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Eleve e = new Eleve();
				e = getEleveFromId(rs.getLong("num_eleve"));
				listeEleves.add(e);
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucune liste d'eleve pour le groupe choisi : "+ g.getNom());
			e1.printStackTrace();
		}
		return listeEleves;
	}

	@Override
	public List<Absence> listeAbsences(Long idEleve) {
		List<Absence> listeAbsences = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from absences where id_eleve=?;");
			if (idEleve!=null) {
				ps.setLong(1, idEleve);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Absence a = new Absence();
					a.setCode(rs.getLong("id_absence"));
					a.setGroupe(getGroupeFromId(rs.getLong("id_groupe")));
					a.setSeance(getSeanceFromId(rs.getLong("id_seance")));
					a.setJustifiee(rs.getInt("justifie"));
					listeAbsences.add(a);
				}
				ps.close();
			}
			
		} catch (SQLException e1) {
			System.out.println("Aucune liste d'absence pour l'élève choisi : "+ idEleve);
			e1.printStackTrace();
		}
		return listeAbsences;
	}

	@Override
	public void faireAppel(Seance s, Enseignant e, Groupe g, List<Eleve> listeElevesAbsents) {
		confirmerAppel(listeElevesAbsents, s, g);
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(" UPDATE seance SET appel=1 WHERE id_seance=?;");
			ps.setLong(1,s.getCode());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e1) {

			System.out.println("Absences ne sont pas enregistrées ");
			e1.printStackTrace();
		}
	}

	@Override
	public void confirmerAppel(List<Eleve> listeElevesAbsents, Seance s, Groupe g) {
		for (Eleve eleve : listeElevesAbsents) {
			ajouterAbsence(eleve, s, g);
		}
	}

	@Override
	public void ajouterAbsence(Eleve e, Seance s, Groupe g) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from absences where id_seance=? and id_eleve=?;");
			ps.setLong(1, s.getCode()); 
			ps.setLong(2, e.getId());
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				ps = conn.prepareStatement("INSERT INTO `absences`(`Id_eleve`, `Id_groupe`, `Id_seance`, `Justifie`) VALUES"
						+ " (?,?,?,?)" );
				ps.setLong(1,e.getId());
				ps.setLong(2,g.getNum());
				ps.setLong(3,s.getCode());
				ps.setInt(4,0);
				ps.executeUpdate();
				ps.close();
			}
			
		} catch (SQLException e1) {
			System.out.println("Absence n'a pas été ajouté à l'élève "+e.getNom());
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public Enseignant getEnseignantFromId(Long id_enseignant) {
		Enseignant e = new Enseignant();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from enseignant where id_enseignant=?;");
			ps.setLong(1, id_enseignant);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				e.setCode(id_enseignant);
				e.setNom(rs.getString("nom_enseignant"));
				e.setPrenom(rs.getString("prenom_enseignant"));
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucun enseignant avec cet id : " + id_enseignant);
			e1.printStackTrace();
		}
		return e;
	}
	
	public Enseignant getEnseignantFromLogin(String username, String password) {
		Enseignant e = new Enseignant();
		Connection conn = SingletonConnection.getConnection();
		int i = 1;
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from enseignant where nom_enseignant=? and mdp=?;");
			ps.setString(1, username);  
			ps.setString(2, password);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				i=0;
				e.setCode(rs.getLong("id_enseignant"));
				e.setNom(rs.getString("nom_enseignant"));
				e.setPrenom(rs.getString("prenom_enseignant"));
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucun enseignant avec ce username : " + username);
			e1.printStackTrace();
		}
		if (i==1) return null;
		return e;
	}
	
	@Override
	public Eleve getEleveFromLogin(String username, String password) {
		Eleve e = new Eleve();
		Connection conn = SingletonConnection.getConnection();
		int i = 1;
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from eleve where num_eleve=? and mdp=?;");
			ps.setString(1, username);  
			ps.setString(2, password);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				i=0;
				e.setId(rs.getLong("id_eleve"));
				e.setCode(rs.getLong("num_eleve"));
				e.setNom(rs.getString("nom_eleve"));
				e.setPrenom(rs.getString("prenom_eleve"));
				e.setListeAbsences(listeAbsences(e.getId()));
			}
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Aucun eleve avec ce username : " + username);
			e1.printStackTrace();
		}
		if (i==1) return null;
		return e;
	}

	@Override
	public List<Absence> allAbsences() {
		List<Absence> allAbsences = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from absences");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Absence a = new Absence();
				a.setCode(rs.getLong("id_absence"));
				a.setEleve(getEleveFromIdd(rs.getLong("id_eleve")));
				a.setGroupe(getGroupeFromId(rs.getLong("id_groupe")));
				a.setSeance(getSeanceFromId(rs.getLong("id_seance"))); 
				a.setJustifiee(rs.getInt("justifie"));
				allAbsences.add(a);
			}
			ps.close();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return allAbsences;
	}

	@Override
	public void justifierAbsence(Long id_absence) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(" UPDATE absences SET justifie=1 WHERE id_absence=?;");
			ps.setLong(1,id_absence);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {
			System.out.println("Absence n'est pas justifiée ");
			e1.printStackTrace();
		}	
	}

	@Override
	public int nombreEtudiantByGroupe(Groupe g) {
		int n = 0;
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select count(*)\r\n" + 
					"FROM  groupe_eleve\r\n" + 
					"INNER JOIN eleve\r\n" + 
					" ON eleve.Id_eleve = groupe_eleve.Id_eleve \r\n" + 
					"where groupe_eleve.Id_groupe =?;");
			ps.setLong(1, g.getNum());  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("count(*)");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Seance> listeSeancesByEnseigant(Enseignant e) {
		List<Seance> listeSeances = new ArrayList<>();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id_seance FROM `seance` WHERE Id_enseignant=?;");
			ps.setLong(1, e.getCode());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Seance s = new Seance();
				s = getSeanceFromId(rs.getLong("id_seance"));
				s.setEnseignant(e);
				listeSeances.add(s);
			}
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Collections.sort(listeSeances, (p1, p2) ->  p1.getHoraire().getDate().compareTo(p2.getHoraire().getDate()));
		return listeSeances;
	}

	@Override
	public Absence getAbsenceFromId(Long id_absence) {
		Absence a = new Absence();
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select * from absences where id_absence=?;");
			ps.setLong(1, id_absence);  
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a.setCode(id_absence);
				a.setEleve(getEleveFromId(rs.getLong("id_eleve")));
				a.setGroupe(getGroupeFromId(rs.getLong("id_groupe")));
				a.setSeance(getSeanceFromId(rs.getLong("id_seance")));
				a.setJustifiee(rs.getInt("justifie"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("Aucune seance avec cet id : " + id_absence);
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public void ajouterjustif(Long id_absence) {
		Connection conn = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(" UPDATE absences SET justifie=2 WHERE id_absence=?;");
			ps.setLong(1,id_absence);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e1) {

			System.out.println("Absence n'est pas justifiée ");
			e1.printStackTrace();
		}
		
	}

	@Override
	public boolean isJustified(Long id_absence) {
		return getAbsenceFromId(id_absence).getJustifiee()==2;
	}
	
}
