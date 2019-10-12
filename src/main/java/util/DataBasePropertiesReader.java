package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBasePropertiesReader {

    private FileInputStream fileInputStream;
    private Properties properties;

    private String url;
    private String username;
    private String password;

    public void readProperties() {
        try {
            fileInputStream = new FileInputStream("./src/main/resources/db.properties");
            properties = new Properties();
            properties.load(fileInputStream);
            this.url = properties.getProperty("db.connection.url");
            this.username = properties.getProperty("db.connection.username");
            this.password = properties.getProperty("db.connection.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
