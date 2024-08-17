package com.spotify.api.pojo.requestmodel.playlist;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddItemsToPlaylistModel {
	List<String> uris;
	int position;
}
