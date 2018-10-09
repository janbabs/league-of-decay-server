package com.janbabs.leagueofdecayserver.model;

public class SummonerMatches
{
    private Match[] matches;

    private String totalGames;

    private String startIndex;

    private String endIndex;

    public Match[] getMatches ()
    {
        return matches;
    }

    public void setMatches (Match[] matches)
    {
        this.matches = matches;
    }

    public String getTotalGames ()
    {
        return totalGames;
    }

    public void setTotalGames (String totalGames)
    {
        this.totalGames = totalGames;
    }

    public String getStartIndex ()
    {
        return startIndex;
    }

    public void setStartIndex (String startIndex)
    {
        this.startIndex = startIndex;
    }

    public String getEndIndex ()
    {
        return endIndex;
    }

    public void setEndIndex (String endIndex)
    {
        this.endIndex = endIndex;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [matches = "+matches+", totalGames = "+totalGames+", startIndex = "+startIndex+", endIndex = "+endIndex+"]";
    }

    public long getLastRankedMatchTimestamp() {
        for (Match match: matches) {
            if ( match.queue.equals("420")) {
                return Long.valueOf(match.timestamp);
            }
        }
        // TODO: 03.10.2018 what if out of 100 games none were ranked
        return -1L;
    }

    static class Match
    {
        private String timestamp;

        private String champion;

        private String queue;

        private String season;

        private String gameId;

        private String role;

        private String platformId;

        private String lane;

        public String getTimestamp ()
        {
            return timestamp;
        }

        public void setTimestamp (String timestamp)
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

        @Override
        public String toString()
        {
            return "ClassPojo [timestamp = "+timestamp+", champion = "+champion+", queue = "+queue+", season = "+season+", gameId = "+gameId+", role = "+role+", platformId = "+platformId+", lane = "+lane+"]";
        }
    }
}
