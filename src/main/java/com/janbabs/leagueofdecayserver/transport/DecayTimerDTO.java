package com.janbabs.leagueofdecayserver.transport;

public class DecayTimerDTO
{
    private Integer daysBeforeDecay;
    private String leagueTier;
    private boolean eligibleForDecay;

    public DecayTimerDTO() {
    }

    public void setDaysBeforeDecay(Integer daysBeforeDecay) {
        this.daysBeforeDecay = daysBeforeDecay;
    }

    public void setLeagueTier(String leagueTier) {
        this.leagueTier = leagueTier;
    }

    public void setEligibleForDecay(boolean eligibleForDecay) {
        this.eligibleForDecay = eligibleForDecay;
    }

    public Integer getDaysBeforeDecay() {
        return daysBeforeDecay;
    }

    public String getLeagueTier() {
        return leagueTier;
    }

    public boolean isEligibleForDecay() {
        return eligibleForDecay;
    }
}
