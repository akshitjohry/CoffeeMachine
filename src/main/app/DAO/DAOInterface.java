package DAO;

import java.util.Optional;

public interface DAOInterface<T> {
    void add(T value);
    Optional<T> get(String key);
}
