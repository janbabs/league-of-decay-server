package com.janbabs.leagueofdecayserver.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "champions")
public class Champion {

    @Column(length = 30)
    private String name;
    @Id
    private Integer id;
    @Column(length = 40)
    private String title;
    @Column(name = "champion_key", length = 30)
    private String key;
    @Column(length = 256)
    private String blurb;

    public Champion() {
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Champion: name: " + name + " id: " + id;
    }
}
