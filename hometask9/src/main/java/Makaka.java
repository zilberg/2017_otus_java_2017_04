import java.sql.*;
import java.util.*;

/**
 * Created by vasvasvlad on 07.06.17.
 */
public class Makaka {
    ////TYty

    public static void main(String[] args) throws
            ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
//1
        DriverManager.registerDriver((Driver)Class.forName("org.postgresql.Driver").newInstance());
        StringBuilder url = new StringBuilder();
        url
                .append("jdbc:postgresql://")
                .append("127.0.0.1:")
                .append("5432/")
                .append("otus?")
                .append("user=vasvasvlad&")
                .append("password=inheritance");
   //2
        try(Connection connection = DriverManager.getConnection(url.toString());){

        }

    Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while(scanner.hasNextInt()){
            i++;
            if(i % 2 == 0){
               list.add(scanner.nextInt());
               continue;
            }
            scanner.nextInt();
        }
        StringBuilder str =new StringBuilder();
        for( int j=list.size()-1; j>=0; j--){
            str.append(list.get(j)).append(" ");
        }

        System.out.println(str);

























        /*StringBuilder url = new StringBuilder();

        url
                .append("jdbc:postgresql://")    //db_type
                .append("127.0.0.1:")           //hostname
                .append("5432/")                 //port
                .append("otus?")                 //db_name
                .append("user=vasvasvlad&")      //user
                .append("password=inheritance"); //password
        try (Connection connection = DriverManager.getConnection(url.toString());
        Statement stmt = connection.createStatement()){
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO users(name, age) VALUES(?, ?)");
            pstmt.setString(1, "Vladimir");
            pstmt.setLong(2,34);
            pstmt.executeUpdate();

            stmt.execute("Select * from users");
            ResultSet rs = stmt.executeQuery("Select * from users");
            while(rs.next()){
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + rs.getInt(3));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
*/
    }



}
