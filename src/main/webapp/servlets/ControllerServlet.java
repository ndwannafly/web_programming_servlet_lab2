package main.webapp.servlets;


import main.webapp.exception.WLException;
import main.webapp.exception.WebLabException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new WebLabException(WLException.METHOD_POST_IS_NOT_SUPPORTED);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("controller here!!!!");
        if(request.getParameter("username") != null && !request.getParameter("username").isEmpty()
                && request.getParameter("password") != null && !request.getParameter("password").isEmpty()){
            System.out.println("dispatching to loginServlet....");
            if(request.getHeader("Authorization").equals("login")) request.getRequestDispatcher("/loginServlet").forward(request, response);
            if(request.getHeader("Authorization").equals("signup")) request.getRequestDispatcher("/signupServlet").forward(request, response);
        }else {
            try {
                double x = Double.parseDouble(request.getParameter("x"));
                double y = Double.parseDouble(request.getParameter("y"));
                double r = Double.parseDouble(request.getParameter("r"));
                request.getRequestDispatcher("/areaCheckServlet").forward(request, response);
            } catch (NullPointerException | NumberFormatException e) {
                throw new WebLabException(WLException.NULL_PARAM);
            }
        }
    }
}
