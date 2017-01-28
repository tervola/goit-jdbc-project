package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.MenuDao;
import ua.com.tervola.jdbc.model.ProjectMenu;

import java.util.List;

/**
 * Created by user on 1/28/2017.
 */
public class JdbcMenuDao implements MenuDao {
    private static Logger LOGGER = LogManager.getLogger(JdbcMenuDao.class);

    private ComboPooledDataSource dataSource;

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createNewMenu() {

    }

    @Override
    public void removeMenu() {

    }

    @Override
    public void modifyMenu(int menu_id) {

    }

    @Override
    public ProjectMenu fingMenuByName(String menuName) {
        return null;
    }

    @Override
    public List<ProjectMenu> findAllMenu() {
        return null;
    }
}
