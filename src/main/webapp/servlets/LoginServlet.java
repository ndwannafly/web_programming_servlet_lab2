package main.webapp.servlets;

import main.webapp.exception.WLException;
import main.webapp.exception.WebLabException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside LoginServlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        ServletContext context  = request.getServletContext();


        if (context.getAttribute("users") == null) {
            context.setAttribute("users", new HashMap<String, String>());
        }

        HashMap<String, String> users = (HashMap<String, String>) context.getAttribute("users");
        if(!users.containsKey(username)){
            System.out.println("WRONG_LOGIN username");
            throw new WebLabException(WLException.WRONG_LOGIN);
        } else{
            if(!users.get(username).equals(password)){
                System.out.println("WRONG_LOGIN password");
                throw new WebLabException(WLException.WRONG_LOGIN);
            }
            else{
                request.getSession().setAttribute("auth", true);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }

    }
}
