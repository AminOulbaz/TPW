package controller;

import beans.Staff;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdministrativeStaff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AuthService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    @Inject
    private AuthService authService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DEBUG: richiesta di login");
        System.out.println(">>>>>>>> SONO ENTRATO NEL METODO <<<<<<<<");
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    private void handleSuccess(HttpServletResponse resp, String message) throws IOException {
        String response = "<div style='color:green;'>"+message+"!</div>";
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(response);
    }

    private void handleCriticalError(HttpServletResponse resp, String message) throws IOException {
        // 500 Internal Server Error
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.getWriter().write("<div style='background:red;color:white;'>"+message+"</div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");

        if (username == null || username.isEmpty() || pwd == null || pwd.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        Staff member = authService.getStaffMember(username);
        String path = getServletContext().getContextPath();
        if (member == null) {
            path = getServletContext().getContextPath() + "/index.html";
        } else {
            req.getSession().setAttribute("member", member);
            String target = member instanceof AdministrativeStaff ? "/GoToHomeAdmin" : "/GoToHomeManagerCollaborator";
            path = path + target;
        }
        resp.sendRedirect(path);
    }
}
