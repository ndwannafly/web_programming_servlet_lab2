package main.webapp.servlets;

import main.webapp.model.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
        ServletContext context = request.getServletContext();
        if (context.getAttribute("queries") == null) {
            context.setAttribute("queries", new ArrayList<>());
        }

        List<Query> queries = (List<Query>) context.getAttribute("queries");
        Query query = new Query(x, y, r);
        queries.add(0, query);
        System.out.println("size: " + queries.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/data-result");

        dispatcher.forward(request, response);
    }
}
