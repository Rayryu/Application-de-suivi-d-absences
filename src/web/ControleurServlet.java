package web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.AbsenceMetierImpl;
import metier.AbsenceMetierInterface;
import metier.Eleve;
import metier.Enseignant;
import metier.Groupe;
import metier.Seance;

public class ControleurServlet extends HttpServlet{
	private AbsenceMetierInterface metier;
	private Enseignant enseignant;
	private Groupe groupe;
	private Seance seance;
	public AbsenceModel model ;
	
	@Override
	public void init() throws ServletException {
		metier = new AbsenceMetierImpl();
		model = new AbsenceModel();
		enseignant = new Enseignant();
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		session.setAttribute("erreur", 99);
		if (action!=null) {
			
			if(action.equals("Choisir un groupe")) {
				 session = req.getSession();
				Long id_groupe = Long.parseLong(req.getParameter("groupeselectionne"));
				Groupe g = metier.getGroupeFromId(id_groupe);
				Enseignant e = (Enseignant) session.getAttribute("enseignant");
				groupe = g;
				model.setGroupe(g);
				List<Seance> listeSeances = metier.listeSeances(model.getGroupe(),  metier.getEnseignantFromId(e.getCode()));
				model.setListeSeances(listeSeances);
				session.setAttribute("groupe", groupe);
				session.setAttribute("listeSeances", listeSeances);
				
			}else if(action.equals("Choisir une seance")) {
				 session = req.getSession();
				session.setAttribute("erreur", 99);
				if (groupe==null) {
					session.setAttribute("erreur", 3);
					
				} else {
					Long id_seance = Long.parseLong(req.getParameter("seanceselectionne"));
					seance = metier.getSeanceFromId(id_seance);
					seance.setEnseignant(enseignant);
					model.setSeance(seance);
					session.setAttribute("seance", seance);
					List<Eleve> listeEleves = metier.listeEleves(groupe);
					model.setListeEleves(listeEleves);
					session.setAttribute("listeEleves", listeEleves);
				}
				
				
			} else if(action.equals("Faire l'appel")) {
				
				if (seance==null) {
					session.setAttribute("erreur", 3);
				}else {
					List<Eleve> le =  model.getListeEleves();
					List<Eleve> listeElevesAbsents = new ArrayList<>();
					for (Eleve eleve : le) {
						Long l  =eleve.getId();
						if (req.getParameter( "absent"+l )!=null) listeElevesAbsents.add(eleve);
					}
					model.setListeElevesAbsents(listeElevesAbsents);
					metier.faireAppel(model.getSeance(), model.getEnseignant(), model.getGroupe(), model.getListeElevesAbsents());
					session = req.getSession();
					session.setAttribute("erreur", 4);
				}
			}
			 
		}
		
		
		req.getRequestDispatcher("VueAbsence.jsp").forward(req, resp);
		
		
	}
	
	

}
