package com.janbabs.leagueofdecayserver.utils;

public enum ServerType {
    RU("RU"),
    KR("KR"),
    BR("BR1"),
    OCE("OCE1"),
    JAP("JP1"),
    NA("NA1"),
    EUNE("EUN1"),
    EUW("EUW1"),
    TR("TR1"),
    LAN("LA1"),
    LAS("LA2");

    public String url;

    ServerType(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }

    boolean isValid(String type) {
        type.toUpperCase();
        return false;
        // TODO: 11.10.2018 write checking enum type
    }
}
