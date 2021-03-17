package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@jakarta.servlet.annotation.WebServlet("Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BufferedReader reader = null;
	
    @Override
    public void init(ServletConfig config) throws ServletException {
    	try {
			reader = new BufferedReader(new FileReader(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Usually would access data base but will do a simple file reader instead instead
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (reader==null) {
			System.out.println("File not Found");
		}
		else {
			String temp;
			String[] split = null;
			while ((temp=reader.readLine())!=null) {
				split = temp.split("\t");
				if(split[0]==username&&split[1]==password) {
					System.out.println("User Found");
					response.setStatus(200);
					return;
				}
			}
			//Resource not found error
			response.setStatus(404);
		}
	}

}
