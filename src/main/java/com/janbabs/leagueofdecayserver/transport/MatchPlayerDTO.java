package com.janbabs.leagueofdecayserver.transport;

import com.janbabs.leagueofdecayserver.utils.LeagueTier;

public class MatchPlayerDTO {
    private String  summonerName;
    private String leagueTier;
    private String rank;
    private String championName;
    private String team;

    private MatchPlayerDTO(String summonerName, String leagueTier, String rank, String championName, String team) {
        this.summonerName = summonerName;
        this.leagueTier = leagueTier;
        this.rank = rank;
        this.championName = championName;
        this.team = team;
    }

    public MatchPlayerDTO() {
    }


    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getLeagueTier() {
        return leagueTier;
    }

    public void setLeagueTier(String leagueTier) {
        this.leagueTier = leagueTier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    static public class PlayerDTOBuilder {
        private String summonerName;
        private String leagueTier;
        private String rank;
        private String championName;
        private String team;

        public PlayerDTOBuilder() {
        }

        public PlayerDTOBuilder summonerName(String name) {
            this.summonerName = name;
            return this;
        }

        public PlayerDTOBuilder leagueTier(String tier) {
            this.leagueTier = tier;
            return this;
        }

        public PlayerDTOBuilder rank(String rank) {
            this.rank = rank;
            return this;
        }

        public PlayerDTOBuilder championName(String championName) {
            this.championName = championName;
            return this;
        }

        public PlayerDTOBuilder team(String team) {
            this.team = team;
            return this;
        }

        public MatchPlayerDTO build() {
            return new MatchPlayerDTO(summonerName, leagueTier, rank, championName, team);
        }


    }

}
