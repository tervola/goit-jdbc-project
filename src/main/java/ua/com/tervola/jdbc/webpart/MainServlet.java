package ua.com.tervola.jdbc.webpart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.controller.EmployeeController;
import ua.com.tervola.jdbc.model.Employee;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public class MainServlet extends HttpServlet {

    @Autowired
    private DatabaseController dataBaseController;

    @Autowired
    private EmployeeController employeeController;

    private static List<String> MENU_LIST = Arrays.asList("tables", "DAOobjects");
    private static List<String> DAO_LIST = Arrays.asList("employeeDAO", "employeeDAO");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
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
                request.setAttribute("error", "Error, during getting info from Database: " + e.toString());
                pageRedirector("/error.jsp", request, response);
            }
        } else if (action.endsWith("daos")){
            request.setAttribute("daos", DAO_LIST);
            pageRedirector("/daoobjects.jsp", request, response);
        } else {

            request.setAttribute("menu", MENU_LIST);
            pageRedirector("/mainPage.jsp", request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if(action.endsWith("modifying")){
            List<Employee> employees = employeeController.getAllEmployees();
            List<List<String>> result= new ArrayList<>();
            for (Employee employee : employees) {

            }

            request.setAttribute("command_result", result);
            pageRedirector("/command_result.jsp", request, response);
        }
    }

    private void pageRedirector(String page,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(page).forward(request,response);
    }

//    public void setDataBaseController(DatabaseController dataBaseController) {
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
