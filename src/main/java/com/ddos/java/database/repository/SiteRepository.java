package com.ddos.java.database.repository;

import com.ddos.java.database.entity.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE (:name IS NULL OR s.name LIKE CONCAT('%',:name,'%'))")
    List<Site> getByName(String name);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s ORDER BY s.status DESC, s.name ASC")
    List<Site> findAll();
}
