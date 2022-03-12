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
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE (:url IS NULL OR s.url LIKE CONCAT('%',:url,'%'))")
    List<Site> getByUrl(String url);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE (:url IS NULL OR s.url LIKE CONCAT('%',:url,'%')) AND s.online =:online")
    List<Site> getByUrlAndOnline(String url, boolean online);

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE s.verified = true ORDER BY s.online DESC, s.cantPing DESC, s.name ASC")
    List<Site> findAll();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE s.verified = true AND s.cantPing = false")
    List<Site> findAllCanPing();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT DISTINCT s FROM Site s WHERE s.verified = false ORDER BY s.id ASC")
    List<Site> findAllUnverified();
}
