package security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/web/*","/api/*"}) // Protegge tutti gli endpoint sotto queste rotte
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if((req.getRequestURI().equals("/web/login") || req.getRequestURI().equals("/api/login"))) {
            chain.doFilter(request, response);
        }
        else
        {
            // 1. Controllo della Sessione
            HttpSession session = req.getSession(false);
            boolean isLoggedIn = (session != null && session.getAttribute("username") != null);

            // 2. Controllo dei Cookie (opzionale, per "Ricordami")
            boolean hasValidCookie = false;
            Cookie[] cookies = req.getCookies();
            if (cookies != null && isLoggedIn) {
                for (Cookie c : cookies) {
                    if ("JSESSIONID".equals(c.getName()) && isValidToken(c.getValue(), session)) {
                        hasValidCookie = true;
                        break;
                    }
                }
            }

            if (isLoggedIn && hasValidCookie) {
                // Connessione valida: prosegui verso la Servlet
                chain.doFilter(request, response);
            }
        }
    }

    //ria application
    private boolean isValidToken(String token, HttpSession session) {
        return token.equals(session.getId());
    }
}
