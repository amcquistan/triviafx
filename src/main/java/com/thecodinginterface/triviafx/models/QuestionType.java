package com.thecodinginterface.triviafx.models;

public enum QuestionType {
    Any("Any", "any"),
    Multiple("Multiple Choice", "multiple"),
    Boolean("True / False", "boolean");

    private String displayName;
    private String apiName;

    QuestionType(String displayName, String apiName) {
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