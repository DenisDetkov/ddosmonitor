package com.ddos.java.database.entity;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "sites")
@ToString
public class Site {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String comment;

    @Column
    private String ip;

    @Column
    private String location;

    @Column(unique = true)
    private String url;

    @Column
    private boolean verified = false;

    @Column
    private boolean cantPing = false;

    @Column
    private boolean online = false;
}
