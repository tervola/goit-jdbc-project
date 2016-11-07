package ua.com.tervola.jdbc.controller;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.jdbc.JdbcEmployeeDao;

import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;
    private JdbcEmployeeDao employeeDao;

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setEmployeeDao(JdbcEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Employee> getAllEmployees(){

        //если уже есть транзакция вернет текущую
        final int propagationRequired = TransactionDefinition.PROPAGATION_REQUIRED;

        //каждый раз будет создана новая транзакция(старая будет суспендится - не будет коммититься)
//        final int propagationRequiredNew = TransactionDefinition.PROPAGATION_REQUIRES_NEW;

        TransactionStatus status = txManager.getTransaction(
                new DefaultTransactionDefinition(propagationRequired));
        try{
            List<Employee> result = employeeDao.findAll();
            txManager.commit(status);
            return result;
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }

    }

    public void find(){
        employeeDao.load(2);
    }
}
