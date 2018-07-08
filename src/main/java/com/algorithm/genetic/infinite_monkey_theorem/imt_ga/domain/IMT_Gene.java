package com.algorithm.genetic.infinite_monkey_theorem.imt_ga.domain;

public class IMT_Gene {

    private String targetPhrase;
    private String ownPhrase;

    public IMT_Gene(String targetPhrase, String ownPhrase) {
        this.targetPhrase = targetPhrase;
        this.ownPhrase = ownPhrase;
    }

    public String getTargetPhrase() {
        return targetPhrase;
    }

    public String getOwnPhrase() {
        return ownPhrase;
    }

    public void setOwnPhrase(String ownPhrase) {
        this.ownPhrase = ownPhrase;
    }
}