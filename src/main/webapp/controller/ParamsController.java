package main.webapp.controller;

import com.sun.istack.internal.NotNull;
import main.webapp.entity.Query;
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
public class ParamsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new WebLabException(WLException.METHOD_POST_IS_NOT_SUPPORTED);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        @NotNull
        String[] pointX = request.getParameter("x").split(",");

        @NotNull
        String[] pointY = request.getParameter("y").split(",");

        @NotNull
        String[] r = request.getParameter("r").split(",");


        Log.log("Form params: [point x: {}], [point y: {}], [r: {}]", pointX, pointY, r);

        ServletContext context = request.getServletContext();

        if (context.getAttribute("queries") == null) {
            context.setAttribute("queries", new ArrayList<>());
        }

        List<Query> queries = (List<Query>) context.getAttribute("queries");

        for(int i = 0; i < r.length; i++) {
            Query query = new Query(pointX[(i + 1 > pointX.length) ? 0 : i], pointY[(i + 1 > pointY.length) ? 0 : i], r[i]);
            queries.add(0, query);
        }
        System.out.println("size: " + queries.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/data-result");

        dispatcher.forward(request, response);
    }
}
