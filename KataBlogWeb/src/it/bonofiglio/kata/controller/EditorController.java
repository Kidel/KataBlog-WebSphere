package it.bonofiglio.kata.controller;

import it.bonofiglio.kata.bean.*;
import it.bonofiglio.kata.utils.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
	private EditorBeanLocal uf;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
		String paramValue = request.getParameter("id");
			if (paramValue==null) {
				out.println("{\"status\": \"success\", \"message\": " + uf.getAllEditors().toString() + "}");
			}
			else 
				out.println("{\"status\": \"success\", \"message\": " + uf.getEditor(Long.parseLong(paramValue)).toString() + "}");
		}
		catch(Exception e) {
			System.out.println("DEBUG: " + e);
			e.printStackTrace();
			out.println("{\"status\": \"error\", \"message\": \"" + e + "\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String body = ServletUtils.getBody(request);
		System.out.println("DEBUG: body: " + body);
		Map<String, String> params = (new JsonConfigParser()).parse(body);
		
		try {
			String name = params.get("name");
			String email = params.get("email");
			String password = params.get("password");
			
			System.out.println("DEBUG: adding Editor with parameters: name=" + name + ", email=" + email + ", password=" + password);
			
			this.uf.createEditor(name, email, password);
			out.println("{\"status\": \"success\"}");
		}
		catch(Exception e) {
			System.out.println("DEBUG: " + e);
			e.printStackTrace();
			out.println("{\"status\": \"error\", \"message\": \"" + e + "\"}");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if(uf.deleteAllEditors())
			out.println("{\"status\": \"success\", \"message\": \"\"}");
		else
			out.println("{\"status\": \"error\", \"message\": \"Error with deleting an editor\"}");
			
	}
}
