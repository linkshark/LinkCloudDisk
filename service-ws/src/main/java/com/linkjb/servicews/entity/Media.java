package com.linkjb.servicews.entity;

import java.io.Serializable;

public class Media implements Serializable {
    private Integer id;

    private String mediaName;

    private String primitiveName;

    private String alias;

    private String scriptWriterName;

    private String directorName;

    private String actors;

    private String premiereDate;

    private String bigMediaKind;

    private String mediaKind;

    private String location;

    private String tvStation;

    private String timeSchadule;

    private String grade;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName == null ? null : mediaName.trim();
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName == null ? null : primitiveName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getScriptWriterName() {
        return scriptWriterName;
    }

    public void setScriptWriterName(String scriptWriterName) {
        this.scriptWriterName = scriptWriterName == null ? null : scriptWriterName.trim();
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName == null ? null : directorName.trim();
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors == null ? null : actors.trim();
    }



    public String getBigMediaKind() {
        return bigMediaKind;
    }

    public void setBigMediaKind(String bigMediaKind) {
        this.bigMediaKind = bigMediaKind == null ? null : bigMediaKind.trim();
    }

    public String getMediaKind() {
        return mediaKind;
    }

    public void setMediaKind(String mediaKind) {
        this.mediaKind = mediaKind == null ? null : mediaKind.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getTvStation() {
        return tvStation;
    }

    public void setTvStation(String tvStation) {
        this.tvStation = tvStation == null ? null : tvStation.trim();
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(String premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getTimeSchadule() {
        return timeSchadule;
    }

    public void setTimeSchadule(String timeSchadule) {
        this.timeSchadule = timeSchadule;
    }

}