package web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.AbsenceMetierImpl;
import metier.AbsenceMetierInterface;
import metier.Admin;
import metier.Eleve;
import metier.Enseignant;
import metier.Groupe;

public class loginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AbsenceMetierInterface metier = new AbsenceMetierImpl();
		
		
		Enseignant e = metier.getEnseignantFromLogin(username, password);
		Eleve etudiant = metier.getEleveFromLogin(username, password);
		Admin a = new Admin(username, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("enseignant", e);
		session.setAttribute("etudiant", etudiant);
		session.setAttribute("admin", a);
		String basePath = new File("").getAbsolutePath();
		session.setAttribute("path", basePath);
		
		if(e!=null) {
			response.sendRedirect("VueAbsence.jsp");
			List<Groupe> listeGroupes = metier.listeGroupes(e);
			session.setAttribute("listeGroupes", listeGroupes);
			
		} else if (etudiant!= null) {
			response.sendRedirect("VueEtudiant.jsp");
		}else if (a.getUsername().equals("admin") && a.getPassword().equals("pass")){
			response.sendRedirect("VueAdmin.jsp");
		}else {
			response.sendRedirect("login.jsp");
			session.setAttribute("erreur", 1);
		}
	
	}
}
