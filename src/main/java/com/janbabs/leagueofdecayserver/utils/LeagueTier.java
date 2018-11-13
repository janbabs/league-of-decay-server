package com.janbabs.leagueofdecayserver.utils;

public enum LeagueTier {
    CHALLANGER(false),
    MASTER(false),
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
