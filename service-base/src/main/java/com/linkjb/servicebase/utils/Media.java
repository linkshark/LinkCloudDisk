package com.linkjb.servicebase.utils;

import java.util.Date;

/**
 * @author sharkshen
 * @data 2019/3/22 14:45
 * @Useage
 */
public class Media {
    private String mediaId;//Id主键
    private String mediaName;//剧名
    private String primitiveName;//原名
    private String alias;//别名
    private String scriptWriterName;//编剧名
    private String directorName;//导演
    private String actors;//主演
    private Date premiereDate;//首映日期
    private String bigMediaKind;//大分类
    private String mediaKind;//小分类
    private String location;//地区
    private String tvStation;//电视台
    private Date timeSchadule;//首映时间
    private String grade;//评分
    private String image;//图片名

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getScriptWriterName() {
        return scriptWriterName;
    }

    public void setScriptWriterName(String scriptWriterName) {
        this.scriptWriterName = scriptWriterName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getBigMediaKind() {
        return bigMediaKind;
    }

    public void setBigMediaKind(String bigMediaKind) {
        this.bigMediaKind = bigMediaKind;
    }

    public String getMediaKind() {
        return mediaKind;
    }

    public void setMediaKind(String mediaKind) {
        this.mediaKind = mediaKind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTvStation() {
        return tvStation;
    }

    public void setTvStation(String tvStation) {
        this.tvStation = tvStation;
    }

    public Date getTimeSchadule() {
        return timeSchadule;
    }

    public void setTimeSchadule(Date timeSchadule) {
        this.timeSchadule = timeSchadule;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Media{" +
                "mediaId='" + mediaId + '\'' +
                ", mediaName='" + mediaName + '\'' +
                ", primitiveName='" + primitiveName + '\'' +
                ", alias='" + alias + '\'' +
                ", scriptWriterName='" + scriptWriterName + '\'' +
                ", directorName='" + directorName + '\'' +
                ", actors='" + actors + '\'' +
                ", premiereDate=" + premiereDate +
                ", bigMediaKind='" + bigMediaKind + '\'' +
                ", mediaKind='" + mediaKind + '\'' +
                ", location='" + location + '\'' +
                ", tvStation='" + tvStation + '\'' +
                ", timeSchadule=" + timeSchadule +
                ", grade='" + grade + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
