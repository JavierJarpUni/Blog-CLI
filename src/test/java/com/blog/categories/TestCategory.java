package com.blog.categories;

public enum TestCategory {
    UNIT("Unit Test"),
    INTEGRATION("Integration Test"),
    CLI("CLI Test"),
    PERSISTENCE("Persistence Test");

    private final String description;

    TestCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
