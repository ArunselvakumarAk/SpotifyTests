package com.spotify.api.pojo.requestmodel.playlist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Track {
	private String uri;
}
