package dbService;

import dao.UsersDAO;
import dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vasvasvlad on 07.06.17.
 */
public class DBService {
    private final Connection connection;
    public DBService(){
        this.connection = getPGConnection();
    }
    public UsersDataSet getUser(long id) throws DBException{
        try{
            return (new UsersDAO(connection).get(id));
        }catch (SQLException e){
            throw new DBException(e);
        }
    }


    public static Connection getPGConnection(){
        try{
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbs:postgresql://").
                    append("localhost:").
                    append("5432/").
                    append("otus?").
                    append("user=vasvasvlad").
                    append("&password=inheritance");
            Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        }catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){
         e.printStackTrace();
        }

        return null;
    }
}
