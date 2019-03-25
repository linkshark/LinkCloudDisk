package com.linkjb.servicebase.pojo;

public class LinkMedia {
    private Integer id;

    private Integer linkId;

    private String urlName;

    private String urlAddress;

    private String size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName == null ? null : urlName.trim();
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress == null ? null : urlAddress.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "LinkMedia{" +
                "id=" + id +
                ", linkId=" + linkId +
                ", urlName='" + urlName + '\'' +
                ", urlAddress='" + urlAddress + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}