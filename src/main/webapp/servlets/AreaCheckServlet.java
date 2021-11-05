package main.webapp.servlets;

import main.webapp.model.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");

        HttpSession session = request.getSession();
        if (session.getAttribute("queries") == null) {
            session.setAttribute("queries", new ArrayList<>());
        }

        List<Query> queries = (List<Query>) session.getAttribute("queries");
        Query query = new Query(x, y, r);
        queries.add(0, query);
        System.out.println("size: " + queries.size());

        if(query.getResult().equals("No")) response.setStatus(404);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/data-result");
        dispatcher.forward(request, response);
    }
}
