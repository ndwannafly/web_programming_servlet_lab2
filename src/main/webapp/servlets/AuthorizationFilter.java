package main.webapp.servlets;

import main.webapp.model.Query;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
        filterConfig.getServletContext().setAttribute("code", filterConfig.getInitParameter("code"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("inside filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String code = (String) filterConfig.getServletContext().getAttribute("code");
        System.out.println("code: " + code);
        System.out.println("auth: " + request.getSession().getAttribute("auth"));
        System.out.println("header: " + request.getHeader("Authorization"));

        RequestDispatcher dispatcher = filterConfig.getServletContext().getRequestDispatcher("/index.jsp");

        //login - signup request
        if(request.getHeader("Authorization") != null){
            System.out.println("authorization: " + request.getHeader("Authorization"));
            if (request.getHeader("Authorization").equals("login")) {
                filterChain.doFilter(request, response);
            } else if(request.getHeader("Authorization").equals("signup")){
                filterChain.doFilter(request, response);
            } else if(request.getHeader("Authorization").equals(code)){
                System.out.println("equal to code");
                if(request.getSession().getAttribute("auth") != null && (boolean) request.getSession().getAttribute("auth")){
                    System.out.println("user has logged in");
                } else{
                    System.out.println("Redirecting....!");
                    response.setStatus(401);
                }
                dispatcher.forward(request, response);
            }
            return ;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
