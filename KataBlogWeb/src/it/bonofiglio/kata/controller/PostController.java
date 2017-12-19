package it.bonofiglio.kata.controller;

import it.bonofiglio.kata.bean.PostBeanLocal;
import it.bonofiglio.kata.utils.JsonConfigParser;
import it.bonofiglio.kata.utils.ServletUtils;
import it.bonofiglio.kata.utils.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
	private PostBeanLocal pf;
       
    public PostController() { }

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
			System.out.println("DEBUG: " + e);
			out.println("{\"status\": \"error\", \"message\": \"" + e + "\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String body = ServletUtils.getBody(request);
		System.out.println("DEBUG: body: " + body);
		Map<String, String> params = (new JsonConfigParser()).parse(body);
		
		try {
			String title = params.get("title");
			String content = params.get("content");
			Long authorId = Long.parseLong(params.get("author"));
			String slug = (new StringUtils()).generateSlug(params.get("title"));
			
			this.pf.createPost(title, content, slug, authorId);
			out.println("{\"status\": \"success\"}");
		}
		catch(Exception e) {
			System.out.println("DEBUG: " + e);
			out.println("{\"status\": \"error\", \"message\": \"" + e + "\"}");
		}
	}

}
