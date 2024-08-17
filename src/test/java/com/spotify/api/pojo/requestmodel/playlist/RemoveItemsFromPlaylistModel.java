package com.spotify.api.pojo.requestmodel.playlist;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class RemoveItemsFromPlaylistModel {
	List<Track> tracks;
	String snapshot_id;
}
