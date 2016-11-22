package ua.com.tervola.jdbc.webpart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ua.com.tervola.jdbc.controller.DataBaseController;
import ua.com.tervola.jdbc.controller.EmployeeController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public class MainServlet extends HttpServlet {

    @Autowired
    DataBaseController dataBaseController;

    @Autowired
    EmployeeController employeeController;

    List<String> menuList = Arrays.asList("tables");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        /**
        *   XML Based Configuration:
        */
//        super.init(config);
//        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
//        this.dataBaseController = (DataBaseController) applicationContext.getBean("dataBaseController");


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if( action.endsWith("tables")) {
            List<String> result = null;
            try {
                result = dataBaseController.getAllTables();
                request.setAttribute("result", result);
                pageRedirector("/tables.jsp", request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Error, during getting info from Databese: " + e.toString());
                pageRedirector("/error.jsp", request, response);
            }
        } else {

            request.setAttribute("menu", menuList);
            pageRedirector("/mainPage.jsp", request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        int a = 2;

    }

    private void pageRedirector(String page,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(page).forward(request,response);
    }

//    public void setDataBaseController(DataBaseController dataBaseController) {
//        this.dataBaseController = dataBaseController;
//    }
//
//    public void setEmployeeController(EmployeeController employeeController) {
//        this.employeeController = employeeController;
//    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }
}
