package com.urise.webapp.model;

import java.util.Objects;

public class TextSections extends Section {

    private final String content;


    public TextSections(String content) {
        Objects.requireNonNull(content);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSections that = (TextSections) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return content;
    }
}
