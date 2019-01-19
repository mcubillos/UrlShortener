package com.mcubillos.urlshortener.repository;

import com.mcubillos.urlshortener.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLRepository extends JpaRepository<URL, String> {
    URL findURLByIdKey(String idKey);
    List<URL> findURLByOrderByIdKeyDesc();
}
