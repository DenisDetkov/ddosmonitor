package com.ddos.java.database.repository;

import com.ddos.java.database.entity.BannedIp;
import com.ddos.java.database.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface BannedIpRepository extends JpaRepository<BannedIp, Long> {
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT b FROM BannedIp b")
    List<BannedIp> findAll();

    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query(value = "SELECT b FROM BannedIp b WHERE b.ip =:ip")
    BannedIp findByIp(String ip);
}
