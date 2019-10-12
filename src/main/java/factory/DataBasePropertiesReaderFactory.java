package factory;

import util.DataBasePropertiesReader;

import java.util.Objects;

public class DataBasePropertiesReaderFactory {

    private static DataBasePropertiesReader dataBasePropertiesReader;

    public static DataBasePropertiesReader getInstance(){
        if(Objects.isNull(dataBasePropertiesReader)){
            dataBasePropertiesReader = new DataBasePropertiesReader();
            dataBasePropertiesReader.readProperties();
        }
        return dataBasePropertiesReader;
    }
}
