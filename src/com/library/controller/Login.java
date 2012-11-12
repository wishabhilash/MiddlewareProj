package com.library.controller;

import com.library.config.ConfigLibrary;
import com.library.model.UserInfoModel;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ConfigLibrary configLib = new ConfigLibrary();
       
    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		Map params = request.getParameterMap();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String username = "", password = "";
		boolean authFlag = true;
		
		if (params.containsKey("submit")){
			username = request.getParameter("username");
			password = request.getParameter("password");
			if(!isUserSessionActive(session, username)){
				try{
					authFlag = authentication(session, username, password);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
		
		if(!authFlag) request.setAttribute("auth_error", "Authentication failed!!!");
		
		RequestDispatcher view = request.getRequestDispatcher(isUserSessionActive(session, username) ? 
				"student_dash.jsp" :"login.jsp");
		
		//	Delete the session
		if(configLib.devMode) session.removeAttribute("user");
		
		view.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	boolean authentication(HttpSession session, String username, String password) throws Exception{
		UserInfoModel userModel = new UserInfoModel();
		LinkedHashMap<String, String> param = new LinkedHashMap<String, String>(),
				args = new LinkedHashMap<String, String>();
		param.put("s-password", "");
		args.put("s-username", username);
		try{
			Vector<LinkedHashMap<String, String>> vec = userModel.select(param, args, null);
			if(password.equals(vec.get(0).get("password"))){
				session.setAttribute("user", username);
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	boolean isUserSessionActive(HttpSession session, String username){
		try{
			session.getAttribute("user").equals(username);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
