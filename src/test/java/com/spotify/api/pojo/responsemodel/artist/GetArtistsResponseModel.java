package com.spotify.api.pojo.responsemodel.artist;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetArtistsResponseModel {

    private List<Artist> artists;

}
