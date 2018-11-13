package com.janbabs.leagueofdecayserver.model;

public class Match
{
    private Long timestamp;

    private String champion;

    private String queue;

    private String season;

    private String gameId;

    private String role;

    private String platformId;

    private String lane;

    public Long getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getChampion ()
    {
        return champion;
    }

    public void setChampion (String champion)
    {
        this.champion = champion;
    }

    public String getQueue ()
    {
        return queue;
    }

    public void setQueue (String queue)
    {
        this.queue = queue;
    }

    public String getSeason ()
    {
        return season;
    }

    public void setSeason (String season)
    {
        this.season = season;
    }

    public String getGameId ()
    {
        return gameId;
    }

    public void setGameId (String gameId)
    {
        this.gameId = gameId;
    }

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getPlatformId ()
    {
        return platformId;
    }

    public void setPlatformId (String platformId)
    {
        this.platformId = platformId;
    }

    public String getLane ()
    {
        return lane;
    }

    public void setLane (String lane)
    {
        this.lane = lane;
    }
}
