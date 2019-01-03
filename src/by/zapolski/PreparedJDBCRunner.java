package by.zapolski;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreparedJDBCRunner {
    public static void main(String[] args) {
        ArrayList<Abonent> list = new ArrayList<Abonent>(){
            {
                add(new Abonent(87,1658486,"Кожух Дмитрий"));
                add(new Abonent(51,8866711,"Буйкевич Александр"));
            }
        };
        DataBaseHelper helper = null;
        PreparedStatement statement = null;
        try {
            helper = new DataBaseHelper();
            statement = helper.getPreparedStatement();
            for (Abonent abonent : list){
                helper.insertAbonent(statement,abonent);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            helper.closeStatement(statement);
        }
    }
}
