package main.webapp.servlets;

import com.sun.istack.internal.NotNull;
import main.webapp.model.Query;
import main.webapp.exception.WLException;
import main.webapp.exception.WebLabException;
import main.webapp.log.Log;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/data-process"})
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new WebLabException(WLException.METHOD_POST_IS_NOT_SUPPORTED);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                double x = Double.parseDouble(request.getParameter("x"));
                double y = Double.parseDouble(request.getParameter("y"));
                double r = Double.parseDouble(request.getParameter("r"));
                ServletContext context = request.getServletContext();
                context.getRequestDispatcher("/areaCheckServlet").forward(request, response);
            } catch(NullPointerException | NumberFormatException e){
                throw new WebLabException(WLException.NULL_PARAM);
            }
    }
}
