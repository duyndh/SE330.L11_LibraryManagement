package com.java.project;

public final class InfoEntry {
    public InfoEntry(String label, Class<?> cls, Object value) {
        this.label = label;
        this.cls = cls;
        this.value = value;
    }

    public InfoEntry(String label, Class<?> cls) {
        this.label = label;
        this.cls = cls;
        this.value = null;
    }

    public InfoEntry(String label, Object value) {
        this.label = label;
        this.cls = value.getClass();
        this.value = value;
    }

    private String label;
    private Class<?> cls;
    private Object value;

    public String getLabel() {
        return label;
    }

    public Class<?> getCls() {
        return cls;
    }

    public Object getValue() {
        return value;
    }
}
