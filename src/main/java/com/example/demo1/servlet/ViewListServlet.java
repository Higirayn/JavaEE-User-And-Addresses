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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String clientType = request.getParameter("type");
        String searchText = request.getParameter("search");

        List<Client> clients = selectBean.getClientsFiltered(clientType, searchText);
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }
}