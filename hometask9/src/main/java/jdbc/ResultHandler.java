package jdbc.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vasvasvlad on 06.06.17.
 */
public interface ResultHandler<T> {
    T handler(ResultSet resultSet) throws SQLException;
}
