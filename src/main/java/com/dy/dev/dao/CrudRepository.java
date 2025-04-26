package com.dy.dev.dao;

import java.util.Optional;

public interface CrudRepository<K, T> {

    Optional<T> findById(K id);
}
