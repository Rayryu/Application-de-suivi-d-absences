package web;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import metier.Absence;
import metier.AbsenceMetierImpl;
import metier.AbsenceMetierInterface;
import metier.Eleve;
import metier.Enseignant;
import metier.Groupe;
import metier.Seance;

@MultipartConfig
@WebServlet("/UploadJustifServlet")


public class UploadJustifServlet extends HttpServlet{
	private AbsenceMetierInterface metier;
	private Enseignant enseignant;
	private Groupe groupe;
	public AbsenceModel model ;
    private ServletFileUpload uploader = null;
	
	@Override
	public void init() throws ServletException {
		metier = new AbsenceMetierImpl();
		model = new AbsenceModel();
		enseignant = new Enseignant();
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
		
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
			
			if(action.equals("Choisir une absence")) {
				
				Long id_absence = Long.parseLong(req.getParameter("absenceselectionne"));
				Absence a = metier.getAbsenceFromId(id_absence);
				
				model.setAbsence(a);
				session.setAttribute("absence", a);
				session.setAttribute("model", model);
				
			}
				
		}
		
		req.getRequestDispatcher("UploadJustif.jsp").forward(req, resp);
		
	}
	
}
