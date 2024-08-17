package com.spotify.api.pojo.responsemodel.artist;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artist {

    private ExternalUrls external_urls;
    private Followers followers;
    private List<String> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private Integer popularity;
    private String type;
    private String uri;

}
