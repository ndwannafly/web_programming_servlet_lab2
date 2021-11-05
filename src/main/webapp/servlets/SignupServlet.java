package main.webapp.servlets;

import main.webapp.exception.WLException;
import main.webapp.exception.WebLabException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside SignupServlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        ServletContext context  = request.getServletContext();


        if (context.getAttribute("users") == null) {
            context.setAttribute("users", new HashMap<String, String>());
        }

        HashMap<String, String> users = (HashMap<String, String>) context.getAttribute("users");

        for(Map.Entry<String, String> entry : users.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("");
        if(users.containsKey(username)){
            System.out.println("WRONG_Signup username");
            throw new WebLabException(WLException.WRONG_LOGIN);
        } else{
            users.put(username, password);
            request.getSession().setAttribute("auth", true);
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }

    }
}
