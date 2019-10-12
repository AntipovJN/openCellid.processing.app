package factory;

import service.OpenCellIdService;
import service.OpenCellIdServiceImpl;

import java.sql.SQLException;
import java.util.Objects;

public class OpenCellIdServiceFactory {

    private static OpenCellIdService openCellIdService;

    public static OpenCellIdService getInstance() throws SQLException {
        if(Objects.isNull(openCellIdService)){
            openCellIdService = OpenCellIdServiceImpl.build();
        }
        return openCellIdService;
    }
}
