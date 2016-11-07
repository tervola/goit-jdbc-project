package ua.com.tervola.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.tervola.jdbc.controller.EmployeeController;
import ua.com.tervola.jdbc.model.EmployeeDao;

/**
 * Created by user on 11/3/2016.
 */
public class MainClass {
    private EmployeeController employeeController;
    private Logger LOGGER = LogManager.getLogger(MainClass.class);
    EmployeeDao employeeDao;

    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MainClass main = context.getBean(MainClass.class);
        main.start();

    }



    private void start() throws ClassNotFoundException {
        LOGGER.info("prepared to call to DAO");
        employeeDao = new EmployeeDao();
        employeeDao.findAll().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeDao.findById(1));
    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }
}
