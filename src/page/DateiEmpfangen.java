package page;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Part file = request.getPart("file");
		Path folder = Paths.get("C:/Users/User/CSV-Dateien");
		Files.createDirectories(folder);
		Path filePath = Paths.get(folder.toString(), file.getName());
		Files.createFile(filePath);
		file.write(filePath.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
