package factory;

import dao.OpenCellIdDao;
import dao.jdbcImpl.OpenCellIdDaoJDBCImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class OpenCellIdDaoFactory {

    private static final Logger logger = Logger.getLogger(OpenCellIdDaoFactory.class);

    private static OpenCellIdDao openCellIdDao;

    public static OpenCellIdDao getInstance(Connection connection) {
        if(Objects.isNull(openCellIdDao)) {
            try {
                openCellIdDao = new OpenCellIdDaoJDBCImpl(connection);
            } catch (SQLException e) {
               logger.error("Failed initialization of DAO Layer", e);
            }
        }
        return openCellIdDao;
    }
}
