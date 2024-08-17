package com.spotify.api.constants;

public enum EndPoint {
	ARTIST("v1/artists/"),
    PLAYLIST("v1/playlists/");

    private final String type;

    EndPoint(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
