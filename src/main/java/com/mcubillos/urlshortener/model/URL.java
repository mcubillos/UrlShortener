package com.mcubillos.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "url")
public class URL implements Serializable {
    @Id
    private String idKey;
    private String urlKey;

    public URL() {
    }

    public URL(String idKey, String urlKey) {
        this.idKey = idKey;
        this.urlKey = urlKey;
    }

    public String getUrlKey() {
        return urlKey;
    }
}
