package com.ddos.java.database.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SiteDTO {
    private String name;
    private String comment;
    private String url;
    private String ip;
    private String location;
    private boolean cantPing = false;
    private boolean online = false;
}
