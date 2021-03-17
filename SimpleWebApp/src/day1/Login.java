package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@jakarta.servlet.annotation.WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BufferedReader reader = null;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	//reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("users")));
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Usually would access data base but will do a simple file reader instead instead
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		if (reader==null) {
//			System.out.println("File not Found");
//		}
//		else {
//			String temp;
//			String[] split = null;
//			while ((temp=reader.readLine())!=null) {
//				split = temp.split("\t");
		System.out.println(username);
		System.out.println(password);
				if("username".equals(username)&&"password".equals(password)) {
					System.out.println("User Found");
					response.setStatus(200);
					response.sendRedirect("success.html");
					return;
				}
//			}
			//Resource not found error
			response.setStatus(404);
			response.sendRedirect("index.html");
//		}
	}

}
