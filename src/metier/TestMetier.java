package metier;

import java.util.List;

public class TestMetier {
	public static void main(String[] args) {
		AbsenceMetierInterface absc = new AbsenceMetierImpl();
		Enseignant e = new Enseignant(1L, "", "", null, null);
		
		List<Groupe> l = absc.listeGroupes(e);
		for (Groupe groupe : l) {
			System.out.println(groupe.getNom());
		}
		
		Horaire h = new Horaire();
		h = absc.getHoraireFromId(1L);
		System.out.println(h.getHeureDebut());
		System.out.println(h.getHeureFin());
		System.out.println(h.getDate());
		
		Groupe g = l.get(1);
		
		System.out.println(g.getNum());
		
		List<Seance> ls = absc.listeSeances(g, e);

		for (Seance seance : ls) {
			seance.infoSeance();
		}
		
		g.setNum(1L);
		
		List<Eleve> le = absc.listeEleves(g);
		
		Eleve e1 = le.get(0);
		e1.setCode(1L);
		Seance s = ls.get(0);
		
//		for (Eleve eleve : le) {
//			eleve.infoEleve();
//		}
		
		absc.faireAppel(s, e, g, le);
		
		System.out.println(absc.getEnseignantFromId(1L).getNom());
		System.out.println("******************************");
		Eleve eleve = absc.getEleveFromId(56718L) ;
		System.out.println(absc.allAbsences().size());
		
		absc.justifierAbsence(121L);
		
		System.out.println(absc.nombreEtudiantByGroupe(g));
		
	}
	
}
