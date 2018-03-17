package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
@MultipartConfig
@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AbsenceMetierInterface metier;
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		metier = new AbsenceMetierImpl();
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("erreur", 99);
		if (session.getAttribute("absence")==null) {
			session.setAttribute("erreur", 5);
		}else {
			if(!ServletFileUpload.isMultipartContent(request)){
				throw new ServletException("Content type is not multipart/form-data");
			}

			try {
				List<FileItem> fileItemsList = uploader.parseRequest(request);
				Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
				while(fileItemsIterator.hasNext()){
					Absence a = (Absence) session.getAttribute("absence");
					if (a==null) {
						session.setAttribute("erreur", 5);
					}else {
						FileItem fileItem = fileItemsIterator.next();
						Long id = a.getCode();
						metier.ajouterjustif(id);
						String basePath = new File("").getAbsolutePath();
						session.setAttribute("path", basePath);
						File file = new File(basePath+"\\"+id+"."+fileItem.getContentType().split("/")[1]);
						System.out.println(basePath+"\\"+id+"."+fileItem.getContentType().split("/")[1]);
						//File file = new File("D:\\wildfly-11.0.0.Final\\bin\\null\\tmpfiles\\"+id+"."+fileItem.getContentType().split("/")[1]);
						fileItem.write(file);
						session.setAttribute("erreur", 6);
					}
				}
			} catch (Exception e) {}
		}
		request.getRequestDispatcher("UploadJustif.jsp").forward(request, response);
	}

}