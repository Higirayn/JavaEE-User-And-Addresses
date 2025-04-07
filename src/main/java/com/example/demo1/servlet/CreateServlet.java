package com.example.demo1.servlet;

import com.example.demo1.ejb.UpdateBean;
import com.example.demo1.utils.ValidationUtils;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String clientName = request.getParameter("client_name");
        String type = request.getParameter("type");
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String model = request.getParameter("model");
        String addressText = request.getParameter("address");

        List<String> errors = new ArrayList<>();

        if (!ValidationUtils.isValidClientName(clientName)) errors.add("Некорректное имя клиента.");
        if (!ValidationUtils.isValidType(type)) errors.add("Недопустимый тип.");
        if (!ValidationUtils.isValidIp(ip)) errors.add("Некорректный IP.");
        if (!ValidationUtils.isValidMac(mac)) errors.add("Некорректный MAC.");

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("create.jsp").forward(request, response);
            return;
        }

        updateBean.createClient(clientName, type, ip, mac, model, addressText);
        response.sendRedirect("view");
    }
}