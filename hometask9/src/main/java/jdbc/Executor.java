package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by vasvasvlad on 06.06.17.
 */
public class Executor {
    private final Connection connection;

    public Executor(Connection connection){
        this.connection = connection;
    }

    public void save(String update){
        try(Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler) {
        try(Statement stmt = connection.createStatement()){
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handler(result);
        return value;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

}
