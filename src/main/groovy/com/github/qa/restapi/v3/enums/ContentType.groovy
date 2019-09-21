package com.github.qa.restapi.v3.enums

enum ContentType {
    ANY('*/*'),
    JSON('application/json'),
    TEXT('text/plain'),
    VND_GITHUB_V3_JSON('application/vnd.github.v3+json'),
    VND_GITHUB_HAGAR_PREVIEW_JSON('application/vnd.github.hagar-preview+json')

    public final String value

    ContentType(final String value) {
        this.value = value
    }

    @Override
    String toString() {
        return value
    }
}