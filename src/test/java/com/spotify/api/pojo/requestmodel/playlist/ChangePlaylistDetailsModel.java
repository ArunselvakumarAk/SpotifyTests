package com.spotify.api.pojo.requestmodel.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class ChangePlaylistDetailsModel {
	private String name;
	private String description;
	@JsonProperty("public")
    private boolean isPublic;
}
