package com.sevice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Page<T> findAll(Pageable pageable);

    void save(T entity);

    void update(T entity);

    Optional<T> findById(Long id);

    void deleteById(Long id);

    void delete(T entity);
}
