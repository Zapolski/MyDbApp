package by.zapolski;

import java.sql.*;
import java.util.ArrayList;

public class SimpleJDBCRunner {
    public static void main(String[] args) {

        Connection cn = null;
        try {//1
            cn = ConnectorDB.getConnection();
            Statement st = null;
            try {//2
                st =cn.createStatement();
                ResultSet rs=null;
                try {//3
                    ArrayList<Abonent> list = new ArrayList<>();
                    rs = st.executeQuery("SELECT * FROM phonebook");

                    while (rs.next()){
                        int id = rs.getInt(1);
                        int phone = rs.getInt(3);
                        String name = rs.getString(2);
                        list.add(new Abonent(id,phone,name));
                    }
                    if (list.size()>0){
                        list.forEach(System.out::println);
                    }else{
                        System.out.println("Data not found.");
                    }

                    //batch-команды для запусска массива запросов
                    /*cn.setAutoCommit(false);
                    st = cn.createStatement();
                    st.addBatch("INSERT INTO phonebook (Phone,LastName) VALUES (5642032, 'Гончаров Иван');");
                    st.addBatch("INSERT INTO phonebook (Phone,LastName) VALUES (5642042, 'Гончарова Анна');");
                    int[] updateCounts = st.executeBatch();
                    cn.commit();*/

                    //batch-команды для запусска массива запросов для PreparedStatement
                    /*PreparedStatement prepSt = cn.prepareStatement("INSERT INTO phonebook (Phone,LastName) VALUES (?,?);");
                    try{
                        ArrayList<Abonent> abonents = new ArrayList<Abonent>(){
                            {
                                add(new Abonent(87,1658486,"Кожух Ольга"));
                                add(new Abonent(51,8866711,"Буйкевич Елена"));
                            }
                        };
                        for (Abonent abonent:abonents){
                            prepSt.setInt(1,abonent.getPhone());
                            prepSt.setString(2,abonent.getLastName());
                            prepSt.addBatch();
                        }
                        int[] updateCounts = prepSt.executeBatch();
                    }catch (BatchUpdateException e){
                        e.printStackTrace();
                    }*/



                    //доступ к хранимой процедуре getlastname
                    /*CallableStatement cs = cn.prepareCall("{call getlastname (?,?)}");
                    cs.setInt(1,5875277);
                    cs.registerOutParameter(2, Types.VARCHAR);
                    cs.execute();
                    System.out.println(cs.getString(2));
                    cs.close();*/

                }finally {//for 3
                    if (rs!=null){
                        rs.close();
                    }else{
                        System.err.println("Ошибка во время чтения из БД!");
                    }
                }
            } finally {
                //закрыть Statement, если он был открыт или ошибка при его создани
                if (st !=null){//for 2
                    st.close();
                }else{
                    System.err.println("Statement не создан");
                }
            }
        }catch (SQLException e){//for 1
            System.err.println("DB connection error: "+e);
        }finally {
            if (cn!=null){
                try {
                    cn.close();
                }catch (SQLException e){
                    System.err.println("Connection close error: "+e);
                }
            }
        }
    }

}
