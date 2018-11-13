package com.janbabs.leagueofdecayserver.model;

import com.janbabs.leagueofdecayserver.exception.NoRankedMatchException;

public class SummonerMatches
{
    public static final String QUEUETYPEOFRANKEDMATCH = "420";
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

    public long getLastRankedMatchTimestamp() throws NoRankedMatchException {
        for (Match match :
                matches) {
            if (match.getQueue().equals(QUEUETYPEOFRANKEDMATCH)) {
                return match.getTimestamp();
            }
        }
        throw new NoRankedMatchException();
    }
}
