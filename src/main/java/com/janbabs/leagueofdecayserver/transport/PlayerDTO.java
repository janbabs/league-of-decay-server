package com.janbabs.leagueofdecayserver.transport;

public class PlayerDTO {
    private Long accountId;
    //Summoner ID
    private Long id;
    //Summoner Name
    private String name;
    //Profile Icon ID
    private Integer profileIconId;
    //Date in milliseconds since last profile update
    private Long revisionDate;
    //Summoner Level;
    private Long summonerLevel;
    //Server of the summoner
    private String serverType;


    public PlayerDTO() {
    }

    public PlayerDTO(Long accountId, String serverType) {
        this.accountId = accountId;
        this.serverType = serverType;
    }

    public PlayerDTO(String name, String serverType) {
        this.name = name;
        this.serverType = serverType;
    }

    public PlayerDTO(long accountId, long id, String name, int profileIconId, Long revisionDate, Long summonerLevel, String serverType) {
        this.accountId = accountId;
        this.id = id;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
        this.serverType = serverType;
    }



    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public Long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
}
