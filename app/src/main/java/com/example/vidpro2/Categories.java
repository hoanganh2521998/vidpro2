package com.example.vidpro2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("publisher_id")
    @Expose
    private String publisherId;
    @SerializedName("content_type")
    @Expose
    private String contentType;
    @SerializedName("tab")
    @Expose
    private String tab;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("user_created")
    @Expose
    private String userCreated;
    @SerializedName("user_modified")
    @Expose
    private String userModified;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private Object dateModified;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("lft")
    @Expose
    private String lft;
    @SerializedName("rgt")
    @Expose
    private String rgt;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("short_code")
    @Expose
    private String shortCode;
    @SerializedName("command_code")
    @Expose
    private String commandCode;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("finished_message")
    @Expose
    private String finishedMessage;
    @SerializedName("help_message")
    @Expose
    private String helpMessage;
    @SerializedName("icash")
    @Expose
    private String icash;
    @SerializedName("thumb")
    @Expose
    private String thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserModified() {
        return userModified;
    }

    public void setUserModified(String userModified) {
        this.userModified = userModified;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getDateModified() {
        return dateModified;
    }

    public void setDateModified(Object dateModified) {
        this.dateModified = dateModified;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLft() {
        return lft;
    }

    public void setLft(String lft) {
        this.lft = lft;
    }

    public String getRgt() {
        return rgt;
    }

    public void setRgt(String rgt) {
        this.rgt = rgt;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFinishedMessage() {
        return finishedMessage;
    }

    public void setFinishedMessage(String finishedMessage) {
        this.finishedMessage = finishedMessage;
    }

    public String getHelpMessage() {
        return helpMessage;
    }

    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public String getIcash() {
        return icash;
    }

    public void setIcash(String icash) {
        this.icash = icash;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

}
