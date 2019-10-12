
import entity.OpenCellId;
import factory.OpenCellIdParserFactory;
import factory.OpenCellIdServiceFactory;
import parser.OpenCellIdParser;
import service.OpenCellIdService;
import util.DialogPlatform;

import java.sql.SQLException;

import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        OpenCellIdParser openCellIdParser = OpenCellIdParserFactory.getInstance();
        List<OpenCellId> openCellIds = openCellIdParser.
                    parseFile("./src/main/resources/csv/255.csv");
        OpenCellIdService service = OpenCellIdServiceFactory.getInstance();
        System.out.println("Check for multithreading write to db");
        for (int i = 0; i < 12; i++) {
            service.loadToDb(openCellIds, new String[]{"","","","","","",""});
            System.out.println("Writing...");
        }
        Thread.sleep(10000);
        System.out.println("\n\nCheck filters");
        service.loadToDb(openCellIds, DialogPlatform.setupFilters());
    }
}
