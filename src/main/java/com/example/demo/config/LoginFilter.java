package com.example.demo.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        boolean isLoginPage = path.endsWith("/login") || path.endsWith("/checkLogin");

        // Ne pas filtrer la page de login et le traitement du login
        if (isLoginPage) {
            chain.doFilter(request, response);
            return;
        }

        // Vérifier si l'utilisateur est connecté
        Object user = req.getSession().getAttribute("currentUser");

        if (user == null) {
            // Rediriger vers la page de login
            res.sendRedirect(req.getContextPath() + "/bibliothecaire/login");
        } else {
            // Continuer normalement
            chain.doFilter(request, response);
        }
    }
}
