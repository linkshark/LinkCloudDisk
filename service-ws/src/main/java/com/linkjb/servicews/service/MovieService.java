package com.linkjb.servicews.service;

import com.linkjb.servicews.entity.LinkMedia;
import com.linkjb.servicews.entity.Media;

import java.util.List;

public interface MovieService {
    List<Media> getMediaByText(String Text);

    List<LinkMedia> getLinkMediaByLinkId(String linkId);

}
