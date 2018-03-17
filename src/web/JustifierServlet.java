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

public class JustifierServlet extends HttpServlet{
	private AbsenceMetierInterface metier;
	private Enseignant enseignant;
	private Groupe groupe;
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
		if (action!=null) {
			
			if (action.equals("Justifier")) {
				
				Long id_absence = Long.parseLong(req.getParameter("id_absence"));
				metier.justifierAbsence(id_absence);
				

			}
			req.setAttribute("model", model);
		}
		
		
		req.getRequestDispatcher("VueAdmin.jsp").forward(req, resp);
		
		
	}
	
	

}
