package page;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import analysis.Analysis;
import analysis.AnalysisList;
import analysis.Database;



/**
 * Servlet implementation class DateiEmpfangen
 */
@WebServlet("/DateiEmpfangen")
@MultipartConfig(	fileSizeThreshold=1024*1024*2,	//2  MB
					maxFileSize=1024*1024*10,		//10 MB
					maxRequestSize=1024*1024*50) 	//50 MB
public class DateiEmpfangen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateiEmpfangen() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part csvPart = request.getPart("file");
		Analysis analysis = null;
		Database db = Database.getInstance();
		boolean error = false;
		
		try {
			analysis = new Analysis(csvPart.getInputStream(), csvPart.getSubmittedFileName());
		} catch (Exception e) {
			RequestDispatcher view = request.getRequestDispatcher("DateiHochladenError.jsp");
			view.forward(request, response);
			error = true;
		}
		
		if(!error) {
			AnalysisList list = db.getList();
			list.add(analysis);
			db.setList(list);
			
			request.getSession().setAttribute("list", list);
			request.getSession().setAttribute("index", 0);
			
			
			RequestDispatcher view = request.getRequestDispatcher("analysis.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
