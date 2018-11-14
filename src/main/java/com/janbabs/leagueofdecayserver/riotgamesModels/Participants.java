package com.janbabs.leagueofdecayserver.riotgamesModels;

public class Participants
{
    private String[] gameCustomizationObjects;

    private String bot;

    private String spell2Id;

    private Perks perks;

    private String profileIconId;

    private String summonerName;

    private Integer championId;

    private String teamId;

    private String summonerId;

    private String spell1Id;

    public String[] getGameCustomizationObjects ()
    {
        return gameCustomizationObjects;
    }

    public void setGameCustomizationObjects (String[] gameCustomizationObjects)
    {
        this.gameCustomizationObjects = gameCustomizationObjects;
    }

    public String getBot ()
    {
        return bot;
    }

    public void setBot (String bot)
    {
        this.bot = bot;
    }

    public String getSpell2Id ()
    {
        return spell2Id;
    }

    public void setSpell2Id (String spell2Id)
    {
        this.spell2Id = spell2Id;
    }

    public Perks getPerks ()
    {
        return perks;
    }

    public void setPerks (Perks perks)
    {
        this.perks = perks;
    }

    public String getProfileIconId ()
    {
        return profileIconId;
    }

    public void setProfileIconId (String profileIconId)
    {
        this.profileIconId = profileIconId;
    }

    public String getSummonerName ()
    {
        return summonerName;
    }

    public void setSummonerName (String summonerName)
    {
        this.summonerName = summonerName;
    }

    public Integer getChampionId ()
    {
        return championId;
    }

    public void setChampionId (Integer championId)
    {
        this.championId = championId;
    }

    public String getTeamId ()
    {
        return teamId;
    }

    public void setTeamId (String teamId)
    {
        this.teamId = teamId;
    }

    public String getSummonerId ()
    {
        return summonerId;
    }

    public void setSummonerId (String summonerId)
    {
        this.summonerId = summonerId;
    }

    public String getSpell1Id ()
    {
        return spell1Id;
    }

    public void setSpell1Id (String spell1Id)
    {
        this.spell1Id = spell1Id;
    }

    @Override
    public String toString()
    {
        return "Participants [gameCustomizationObjects = "+gameCustomizationObjects+", bot = "+bot+", spell2Id = "+spell2Id+", perks = "+perks+", profileIconId = "+profileIconId+", summonerName = "+summonerName+", championId = "+championId+", teamId = "+teamId+", summonerId = "+summonerId+", spell1Id = "+spell1Id+"]";
    }

    class Perks
    {
        private String perkSubStyle;

        private String perkStyle;

        private String[] perkIds;

        public String getPerkSubStyle ()
        {
            return perkSubStyle;
        }

        public void setPerkSubStyle (String perkSubStyle)
        {
            this.perkSubStyle = perkSubStyle;
        }

        public String getPerkStyle ()
        {
            return perkStyle;
        }

        public void setPerkStyle (String perkStyle)
        {
            this.perkStyle = perkStyle;
        }

        public String[] getPerkIds ()
        {
            return perkIds;
        }

        public void setPerkIds (String[] perkIds)
        {
            this.perkIds = perkIds;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [perkSubStyle = "+perkSubStyle+", perkStyle = "+perkStyle+", perkIds = "+perkIds+"]";
        }
    }
}