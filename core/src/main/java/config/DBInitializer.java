package config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebListener
public class DBInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Recupero il DataSource tramite JNDI
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MariaDBDS");

            // Lo rendo disponibile a tutta l'applicazione tramite il Context
            sce.getServletContext().setAttribute("dataSource", ds);
        } catch (NamingException e) {
            throw new RuntimeException(e); // Commenta momentaneamente questo
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
