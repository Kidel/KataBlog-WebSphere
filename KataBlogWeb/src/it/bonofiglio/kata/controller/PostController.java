package it.bonofiglio.kata.controller;

import it.bonofiglio.kata.repository.EditorFacadeLocal;
import it.bonofiglio.kata.repository.PostFacadeLocal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostController
 */
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PostFacadeLocal pf;
	
	@EJB
	private EditorFacadeLocal uf;
       
    public PostController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
		String paramValue = request.getParameter("id");
		if (paramValue==null) 
			out.println("{\"status\": \"success\", \"message\": " + pf.getAllPosts().toString() + "}");
		else 
			out.println("{\"status\": \"success\", \"message\": " + pf.getPost(Long.parseLong(paramValue)).toString() + "}");
		}
		catch(Exception e) {
			out.println("{\"status\": \"error\", \"message\": " + e.getMessage() + "}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String slug = request.getParameter("slug");
			Long authorId = Long.parseLong(request.getParameter("authorId"));
			
			pf.createPost(title, content, slug, uf.getEditor(authorId));
			out.println("{\"status\": \"success\"}");
		}
		catch(Exception e) {
			out.println("{\"status\": \"error\", \"message\": " + e.getMessage() + "}");
		}
	}

}
