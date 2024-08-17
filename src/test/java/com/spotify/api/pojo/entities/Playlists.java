package com.spotify.api.pojo.entities;

import java.util.List;
import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Builder
public class Playlists {
	List<String> playlists;

//	public List<String> getPlaylists() {
//		return playlists;
//	}
//
//	public Playlists setPlaylists(List<String> playlists) {
//		this.playlists = playlists;
//		return this;
//	}
	
}
