package it.bonofiglio.kata.controller;

import it.bonofiglio.kata.bean.LoginBeanLocal;
import it.bonofiglio.kata.utils.JsonConfigParser;
import it.bonofiglio.kata.utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LoginBeanLocal lf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(this.lf.getCurrentEditor()!=null) 
			out.println("{\"status\": \"success\"}");
		else 
			out.println("{\"status\": \"error\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String body = ServletUtils.getBody(request);
		System.out.println("DEBUG: body: " + body);
		Map<String, String> params = (new JsonConfigParser()).parse(body);
		
		try {
			String email = params.get("email");
			String password = params.get("password");
			
			if(this.lf.login(email, password))
				out.println("{\"status\": \"success\"}");
			else 
				out.println("{\"status\": \"error\", \"message\": \"Invalid username or password\"}");
		}
		catch(Exception e) {
			System.out.println("DEBUG: " + e);
			out.println("{\"status\": \"error\", \"message\": \"" + e + "\"}");
		}
	}

}