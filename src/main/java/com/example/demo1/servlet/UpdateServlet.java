package com.example.demo1.servlet;

import com.example.demo1.ejb.UpdateBean;
import com.example.demo1.model.Client;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @EJB
    private UpdateBean updateBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int clientid = Integer.parseInt(request.getParameter("clientid"));
        String name = request.getParameter("client_name");
        String type = request.getParameter("type");

        // Get address parameters as arrays
        String[] ips = request.getParameterValues("ip");
        String[] macs = request.getParameterValues("mac");
        String[] models = request.getParameterValues("model");
        String[] addresses = request.getParameterValues("address");

        updateBean.updateClient(clientid, name, type, new Date(), ips, macs, models, addresses);
        response.sendRedirect("view");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int clientid = Integer.parseInt(request.getParameter("id"));
        Client client = updateBean.findClientById(clientid);
        request.setAttribute("client", client);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }
}