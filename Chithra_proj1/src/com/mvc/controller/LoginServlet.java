package com.mvc.controller;
 
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.bean.LoginBean;
import com.mvc.dao.LoginDao;
 
public class LoginServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() 
    {
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
 
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
 
        LoginBean loginBean = new LoginBean(); 
 
        loginBean.setUserName(userName); 
         loginBean.setPassword(password);
 
        LoginDao loginDao = new LoginDao(); 
 
   
 
        try
        {
            String userValidate = loginDao.authenticateUser(loginBean);
     
            if(userValidate.equals("Manager_Role"))
            {
                System.out.println("Manager Page");
     
                HttpSession session = request.getSession(); 
                session.setAttribute("Manager", userName); 
                request.setAttribute("userName", userName);
     
                request.getRequestDispatcher("/JSP/Manager.jsp").forward(request, response); //redirect only MANAGERS to Manager Page
            }
          
        
            else if(userValidate.equals("User_Role"))
            {
                System.out.println("User Page");
     
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(10*60);
                session.setAttribute("User", userName);
                request.setAttribute("userName", userName);
     
                request.getRequestDispatcher("/JSP/User.jsp").forward(request, response); //redirect only USERS to User Page
            }
            else
            {
                System.out.println("Error message = "+userValidate);
                request.setAttribute("errMessage", userValidate);
     
                request.getRequestDispatcher("/JSP/Login.jsp").forward(request, response);
            }
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e2)
        {
            e2.printStackTrace();
        }
    } 
}