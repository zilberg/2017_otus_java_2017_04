import sun.net.ConnectionResetException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args)throws
            ClassNotFoundException,InstantiationException, IllegalAccessException, SQLException  {

        Driver driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
        DriverManager.registerDriver(driver);

        StringBuilder url = new StringBuilder();

        url
                .append("jdbc:postgresql://")    //db_type
                .append("127.0.0.1:")           //hostname
                .append("5432/")                 //port
                .append("otus?")                 //db_name
                .append("user=vasvasvlad&")      //user
                .append("password=inheritance"); //password

        Connection connection = DriverManager.getConnection(url.toString());
       // System.out.println(Main.save(connection,"INSERT INTO users (name,age) VALUES('vasy',29)"));

        /*String [] inserts = new String[]{
                "INSERT INTO users (name,age) VALUES('ivan',30)",
                "INSERT INTO users (name,age) VALUES('petrov',29)"
        };
        save(connection, inserts);*/

        Map <String, Integer> nameAndAge = new HashMap<String, Integer>();
        nameAndAge.put("Maksim",33);
        nameAndAge.put("Petr", 55);
        execUpdate(connection, nameAndAge);

        connection.close();

    }

    public static int execUpdate(Connection connection, String update) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        int updated = stmt.getUpdateCount();
        stmt.close();
        return updated;
    }

    public static void execUpdate(Connection connection, String [] updates){
        try{
            connection.setAutoCommit(false);
            int i=0;
            for(String update: updates) {
                Statement stmt = connection.createStatement();
                stmt.execute(update);
                stmt.close();
                System.out.println(i++);
            }
            }catch(SQLException e){
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }catch (SQLException ignore){}
            }
        }

    public static  void execUpdate(Connection connection, Map<String, Integer> idToName){
        try{
            String update = "INSERT INTO users(name, age) values(?, ?)";
            PreparedStatement stmt = connection.prepareStatement(update);
            for (String name: idToName.keySet()){
                stmt.setString(1, name);
                stmt.setInt(2,idToName.get(name));
                stmt.executeUpdate();
            }
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
