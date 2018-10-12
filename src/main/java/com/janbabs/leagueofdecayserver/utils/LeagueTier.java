package com.janbabs.leagueofdecayserver.utils;

public enum LeagueTier {
    CHALLANGER(true),
    MASTER(true),
    DIAMOND(true),
    PLATINUM(true),
    GOLD(false),
    SILVER(false),
    BRONZE(false),
    IRON(false),
    UNRANKED(false);

    private boolean eligible;

    LeagueTier(boolean eligible) {
        this.eligible = eligible;
    }

    public boolean isEligible() {
        return eligible;
    }
}
