package com.janbabs.leagueofdecayserver.model;


import com.google.gson.annotations.SerializedName;
import com.janbabs.leagueofdecayserver.utils.ServerType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {
    //Account ID
    @Id
    private long accountId;
    //Summoner ID
    @Column(name = "summonerId")
    private long id;
    //Summoner Name
    @SerializedName("name")
    private String summonerName;
    //Profile Icon ID
    private int profileIconId;
    //Date in milliseconds since last profile update
    private long revisionDate;
    //Summoner Level;
    private long summonerLevel;
    //Server of the summoner
    private ServerType serverType;

    public Player() {
    }

    public Player(long accountId, long id, String summonerName, int profileIconId, long revisionDate, long summonerLevel, ServerType serverType) {
        this.accountId = accountId;
        this.id = id;
        this.summonerName = summonerName;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
        this.serverType = serverType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }
}
