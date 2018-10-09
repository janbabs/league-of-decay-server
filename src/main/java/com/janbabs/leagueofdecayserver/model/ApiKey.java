package com.janbabs.leagueofdecayserver.model;

import javax.persistence.*;

@Entity
@Table(name = "apikey")
public class ApiKey {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "api_key",length = 50, nullable = false)
    private String value;

    public ApiKey() {
    }

    public ApiKey(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
