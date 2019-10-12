package factory;

import parser.OpenCellIdParser;

import java.util.Objects;

public class OpenCellIdParserFactory {

    private static OpenCellIdParser openCellIdParser;

    public static OpenCellIdParser getInstance() {
        if(Objects.isNull(openCellIdParser)) {
            openCellIdParser = new OpenCellIdParser();
        }
        return openCellIdParser;
    }
}
