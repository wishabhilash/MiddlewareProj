package com.wish.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String DELETE_JSP = "/Delete.jsp";
	private static String EDIT_JSP = "/Edit.jsp";
	private static String SHOW_ALL_JSP = "/ShowAll.jsp";
       
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		Map params = request.getParameterMap();
		if (params.containsKey("delete")){
			forward = DELETE_JSP;
		}else if (params.containsKey("edit")){
			forward = EDIT_JSP;
		}else{
			forward = SHOW_ALL_JSP;
		}
		HttpSession session = request.getSession();
		session.setAttribute("result", "this is the result");
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
