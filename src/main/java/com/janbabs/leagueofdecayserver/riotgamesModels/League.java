package com.janbabs.leagueofdecayserver.riotgamesModels;

import com.janbabs.leagueofdecayserver.utils.LeagueTier;

public class League {
    private LeagueTier leagueTier;
    private String rank;

    public League(LeagueTier leagueTier, String rank) {
        this.leagueTier = leagueTier;
        this.rank = rank;
    }

    public LeagueTier getLeagueTier() {
        return leagueTier;
    }

    public String getRank() {
        return rank;
    }
}
