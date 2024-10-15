package com.postech.lending.client.model.enums;

public enum ProfileState {
    PENDENT("Pendent"),
    ACTIVE("Active"),
    DISABLED("Disabled");

    private final String label;

    ProfileState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
