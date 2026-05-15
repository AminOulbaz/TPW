package controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AuthService;

import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Register.class);
    @Inject
    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Impostiamo il tipo di contenuto predefinito
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String typeStaff = req.getParameter("type");
        String photoLocation = req.getParameter("photo");
        if (username == null || password == null)
            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

        authService.register(username,password,name,surname,photoLocation,typeStaff);
        resp.sendRedirect("/web/login");
    }
}
