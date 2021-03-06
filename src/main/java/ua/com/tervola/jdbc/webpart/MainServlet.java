package ua.com.tervola.jdbc.webpart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.controller.EmployeeController;
import ua.com.tervola.jdbc.controller.TablesController;
import ua.com.tervola.jdbc.model.Employee;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public class MainServlet extends HttpServlet {

    @Autowired
    private DatabaseController dataBaseController;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private TablesController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if (action.endsWith("tables")) {
            List<String> result = null;
            try {
                result = dataBaseController.getAllTables();
                request.setAttribute("result", result);
                pageRedirector("/tables.jsp", request, response);
            } catch (Exception e) {
                request.setAttribute("error", "Error, during getting info from Database: " + e.toString());
                pageRedirector("/error.jsp", request, response);
            }
        } else if (action.endsWith("storage")) {

            List<String> result = controller.getStoreController().findAllIngridientsAsString();
            redirectWithResult(result, getTableName(action), request, response);

        } else if (action.endsWith("menu")) {

            List<String> result = controller.getMenuController().findAllMenuAsString();
            redirectWithResult(result, getTableName(action), request, response);

        } else if (action.endsWith("prepared_dishes")) {

            List<String> result = controller.getPreparedDishesController().findAllPreparedDishesAsString();
            redirectWithResult(result, getTableName(action), request, response);

        } else if (action.endsWith("dish")) {

            List<String> result = controller.getPreparedDishesController().findAllPreparedDishesAsString();
            redirectWithResult(result, getTableName(action), request, response);

        } else if (action.endsWith("employee")) {

            List<String> result = controller.getEmployeeController().getAllEmployeesAsString();
            redirectWithResult(result, getTableName(action), request, response);

        } else if (action.equals("/")) {

            request.setAttribute("menu", TablesController.MENU_LIST);
            pageRedirector("/mainPage.jsp", request, response);
        } else {
            request.setAttribute("error", "Page not found");
            pageRedirector("/error.jsp", request, response);
        }

    }

    private void redirectWithResult(final List<String> result, final String tableName, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (result.size() == 0) {
            request.setAttribute("error", String.format("the table %s has no any records: ", tableName));
            pageRedirector("/error.jsp", request, response);
        } else {
            request.setAttribute(tableName, result);
            request.setAttribute("tableTitle", tableName);
            pageRedirector(String.format("/%s.jsp", tableName), request, response);
        }
    }

    private String getTableName(String action) {
        return action.replace("/", "");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        if (action.endsWith("modifying")) {
            List<Employee> employees = employeeController.getAllEmployees();
            List<List<String>> result = new ArrayList<>();
            for (Employee employee : employees) {

            }

            request.setAttribute("command_result", result);
            pageRedirector("/command_result.jsp", request, response);
        }
    }

    private void pageRedirector(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ServletContext servletContext = getServletContext();
        servletContext.getRequestDispatcher(page).forward(request, response);
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
