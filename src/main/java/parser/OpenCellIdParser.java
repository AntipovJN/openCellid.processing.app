package parser;

import entity.OpenCellId;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class OpenCellIdParser {

    private final Logger logger = Logger.getLogger(OpenCellIdParser.class);

    public List<OpenCellId> parseFile(String path) {
        String line;
        String cvsSplitBy = ",";
        List<OpenCellId> openCellIdList = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader(path))) {
            if (br.readLine() != null) {
                while ((line = br.readLine()) != null) {
                    try {
                        String[] openCellIdValue = line.split(cvsSplitBy);
                        openCellIdList.add(new OpenCellId(openCellIdValue[0],
                                Integer.valueOf(openCellIdValue[1]), Integer.valueOf(openCellIdValue[2]),
                                Integer.valueOf(openCellIdValue[3]), Integer.valueOf(openCellIdValue[4]),
                                Integer.valueOf(openCellIdValue[5]), Double.valueOf(openCellIdValue[6]),
                                Double.valueOf(openCellIdValue[7]), Integer.valueOf(openCellIdValue[8]),
                                Integer.valueOf(openCellIdValue[9]), Integer.valueOf(openCellIdValue[10]),
                                new Timestamp(Long.valueOf(openCellIdValue[11])),
                                new Timestamp(Long.valueOf(openCellIdValue[12])),
                                Integer.valueOf(openCellIdValue[13])));
                    } catch (RuntimeException e) {
                        logger.warn(String.format("Invalid data in line: (%s)",line),e);
                    }
                }
            }
        } catch (IOException e) {
            logger.error(String.format("Failed reading file %s", path), e);
            System.exit(1);
        }
        openCellIdList.remove(0);
        return openCellIdList;
    }
}
