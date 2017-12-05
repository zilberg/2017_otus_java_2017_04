package jdbc;


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
    public Users getUser(long id) throws DBException {
        try{
            return (new UsersDAO(connection).get(id));
        }catch (SQLException e){
            throw new DBException(e);
        }
    }
    public long addUser(String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO dao = new UsersDAO(connection);
            dao.createTable();
            dao.insertUser(name);
            connection.commit();
            return dao.getUserId(name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {

            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }
    public void cleanUp() throws DBException{
        UsersDAO dao = new UsersDAO(connection);
        try{
            dao.dropTable();
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
