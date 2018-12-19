package dal;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T, PK> {

    void add(T object) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(PK id) throws SQLException;

    void update(T object) throws SQLException;

    void delete(T object) throws SQLException;
}
