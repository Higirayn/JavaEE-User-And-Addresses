package com.example.demo1.servlet;


import com.example.demo1.ejb.SelectBean;
import com.example.demo1.model.Client;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/view")
public class ViewListServlet extends HttpServlet {
    @EJB
    private SelectBean selectBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String typeFilter = request.getParameter("typeFilter");
        String searchText = request.getParameter("searchText");

        List<Client> clients;
        if (typeFilter != null && !typeFilter.isEmpty()) {
            clients = selectBean.findByType(typeFilter);
        } else if (searchText != null && !searchText.isEmpty()) {
            clients = selectBean.searchByNameAndAddress(searchText);
        } else {
            clients = selectBean.findAllClients();
        }

        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}