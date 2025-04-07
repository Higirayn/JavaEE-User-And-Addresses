package com.example.demo1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test-db")
public class TestDBServlet extends HttpServlet {
    @PersistenceContext
    private EntityManager em;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Object result = em.createNativeQuery("SELECT 1").getSingleResult();
            resp.getWriter().println("Database connection OK! Result: " + result);
        } catch (Exception e) {
            resp.getWriter().println("Connection failed: " + e.getMessage());
        }
    }
}