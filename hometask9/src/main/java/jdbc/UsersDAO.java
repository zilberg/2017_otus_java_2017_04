package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vasvasvlad on 07.06.17.
 */
public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public Users get(long id) throws SQLException{
        return executor.execQuery("select * from users id = " + id,result -> {
            result.next();
            return new Users(result.getLong(1),result.getString(2), result.getInt(3));
        });
    }

    public long getUserId(String name) throws SQLException{
        return executor.execQuery("SELECT * FROM users WHERE name='" + name + "'", result ->{
           result.next();
           return result.getLong(1);
        });
    }

    public void insertUser(String name ) throws SQLException{
        executor.save("INSERT INTO users(name) values ('" + name + "')");
    }

    public void createTable() throws SQLException{
        executor.save("CREATE TABLE users(" +
                "id SERIAL PRIMARY KEY, " +
                "name CHAR(255)," +
                "age INT NOT NULL" +
                ");");
    }

    public void dropTable() throws SQLException{
        executor.save("drop table users");
    }
}
