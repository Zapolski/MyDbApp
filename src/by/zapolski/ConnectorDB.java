package by.zapolski;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {
    private static final String FILE_PROP_PATH = System.getProperty("user.dir")+ File.separator+
                                                    "src"+File.separator+"database.properties";
    private static final String URL = "url";

    public static Connection getConnection() throws SQLException{

        Properties prop = new Properties();
        String url = "";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        try {
            FileInputStream fis = new FileInputStream(FILE_PROP_PATH);
            prop.load(fis);
            url = prop.getProperty(URL);
            prop.remove(URL);
        } catch (IOException e) {
            System.err.println(e);
        }
        return DriverManager.getConnection(url, prop);
    }
}
