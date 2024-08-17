package com.spotify.api.pojo.responsemodel.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalUrls {

    private String spotify;

}
