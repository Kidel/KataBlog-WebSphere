package it.bonofiglio.kata.controller;

import it.bonofiglio.kata.repository.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private EditorFacadeLocal uf;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
		String paramValue = request.getParameter("id");
		if (paramValue==null) 
			out.println("{status: success, message: " + uf.getAllEditors().toString() + "}");
		else 
			out.println("{status: success, message: " + uf.getEditor(Long.parseLong(paramValue)).toString() + "}");
		}
		catch(Exception e) {
			out.println("{status: error, message: " + e.getMessage() + "}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			uf.createEditor(name, email, password);
			out.println("{status: success}");
		}
		catch(Exception e) {
			out.println("{status: error, message: " + e.getMessage() + "}");
		}
	}

}
