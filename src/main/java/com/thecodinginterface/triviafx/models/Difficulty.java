package com.thecodinginterface.triviafx.models;

public enum Difficulty {
    Any("Any", "any"),
    Easy("Easy", "easy"),
    Medium("Medium", "medium"),
    Hard("Hard", "hard");

    private String displayName;
    private String apiName;

    Difficulty(String displayName, String apiName) {
        this.displayName = displayName;
        this.apiName = apiName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getApiName() {
        return apiName;
    }

    @Override
    public String toString() {
        return String.format(
            "{%s: displayName=%s, apiName=%s}",
            getClass().getSimpleName(),
            displayName,
            apiName
        );
    }
}