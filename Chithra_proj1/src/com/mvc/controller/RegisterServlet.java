package com.mvc.controller;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.mvc.bean.RegisterBean;
import com.mvc.dao.RegisterDao;
 
public class RegisterServlet extends HttpServlet {
  
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
     }
 
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         String fullName = request.getParameter("fullname");
         String email = request.getParameter("email");
         String userName = request.getParameter("username");      
         String password = request.getParameter("password");
         String role = request.getParameter("role");
          
         RegisterBean registerBean = new RegisterBean();
        
         
         registerBean.setFullName(fullName);
         registerBean.setEmail(email);
         registerBean.setUserName(userName);
         registerBean.setPassword(password); 
         registerBean.setUserName(role);
          
         RegisterDao registerDao = new RegisterDao();
          
        
         String userRegistered = registerDao.registerUser(registerBean);
          
         if(userRegistered.equals("SUCCESS"))   
         {
            request.getRequestDispatcher("/Home.jsp").forward(request, response);
         }
         else   
         {
            request.setAttribute("errMessage", userRegistered);
            request.getRequestDispatcher("/Register.jsp").forward(request, response);
         }
     }
}