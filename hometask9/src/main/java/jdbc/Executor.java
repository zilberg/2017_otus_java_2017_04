package jdbc.executor;

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

    public void execUpdate(String update) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler)
        throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handler(result);
        result.close();
        stmt.close();

        return value;
    }

}
