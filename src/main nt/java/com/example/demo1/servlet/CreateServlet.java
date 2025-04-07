package com.example.demo1.servlet;

import com.example.demo1.model.Address;
import com.example.demo1.model.Client;
import com.example.demo1.util.ValidationUtil;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ValidationException;

import java.io.IOException;
import java.util.Date;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @EJB // или другой способ инъекции зависимости
    private ClientService clientService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Client client = new Client();
            client.setClientName(request.getParameter("clientName"));
            client.setType(request.getParameter("type"));
            client.setAdded(new Date());

            Address address = new Address();
            address.setIp(request.getParameter("ip"));
            address.setMac(request.getParameter("mac"));
            address.setModel(request.getParameter("model"));
            address.setAddress(request.getParameter("address"));
            address.setClient(client);

            ValidationUtil.validateClient(client);
            ValidationUtil.validateAddress(address);

            // Сохранение в базу через сервис
            clientService.createClient(client, address);

            request.setAttribute("success", "Клиент успешно создан");
            response.sendRedirect(request.getContextPath() + "/view");
        } catch (ValidationException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/create.jsp").forward(request, response);
        }
    }
}