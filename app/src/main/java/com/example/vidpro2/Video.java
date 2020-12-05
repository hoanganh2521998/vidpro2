package com.example.vidpro2;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable, Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @SerializedName("category_id")
    @Expose
    private Object categoryId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("artist_name")
    @Expose
    private Object artistName;
    @SerializedName("album_name")
    @Expose
    private Object albumName;
    @SerializedName("file_mp4")
    @Expose
    private String fileMp4;
    @SerializedName("file_3gp")
    @Expose
    private Object file3gp;
    @SerializedName("file_mp4_size")
    @Expose
    private String fileMp4Size;
    @SerializedName("file_3gp_size")
    @Expose
    private String file3gpSize;
    @SerializedName("total_downloaded")
    @Expose
    private Object totalDownloaded;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Object duration;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("user_created")
    @Expose
    private String userCreated;
    @SerializedName("user_modified")
    @Expose
    private String userModified;
    @SerializedName("convert_status")
    @Expose
    private String convertStatus;
    @SerializedName("convert_time")
    @Expose
    private Object convertTime;
    @SerializedName("youtube_url")
    @Expose
    private String youtubeUrl;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("download_status")
    @Expose
    private String downloadStatus;
    @SerializedName("fb_download")
    @Expose
    private String fbDownload;
    @SerializedName("icash")
    @Expose
    private String icash;
    @SerializedName("fb_url")
    @Expose
    private Object fbUrl;
    @SerializedName("aws_status")
    @Expose
    private String awsStatus;
    @SerializedName("icash_2")
    @Expose
    private String icash2;
    @SerializedName("price_2")
    @Expose
    private String price2;
    @SerializedName("publisher_category_id")
    @Expose
    private Integer publisherCategoryId;
    @SerializedName("view_clip_gold")
    @Expose
    private Integer viewClipGold;
    @SerializedName("view_clip_icash")
    @Expose
    private Integer viewClipIcash;
    @SerializedName("download_clip_gold")
    @Expose
    private Integer downloadClipGold;
    @SerializedName("download_clip_icash")
    @Expose
    private Integer downloadClipIcash;

    protected Video(Parcel in) {
        id = in.readString();
        providerId = in.readString();
        title = in.readString();
        avatar = in.readString();
        price = in.readString();
        status = in.readString();
        deleted = in.readString();
        copyright = in.readString();
        fileMp4 = in.readString();
        fileMp4Size = in.readString();
        file3gpSize = in.readString();
        description = in.readString();
        dateCreated = in.readString();
        dateModified = in.readString();
        datePublished = in.readString();
        userCreated = in.readString();
        userModified = in.readString();
        convertStatus = in.readString();
        youtubeUrl = in.readString();
        tags = in.readString();
        downloadStatus = in.readString();
        fbDownload = in.readString();
        icash = in.readString();
        awsStatus = in.readString();
        icash2 = in.readString();
        price2 = in.readString();
        if (in.readByte() == 0) {
            publisherCategoryId = null;
        } else {
            publisherCategoryId = in.readInt();
        }
        if (in.readByte() == 0) {
            viewClipGold = null;
        } else {
            viewClipGold = in.readInt();
        }
        if (in.readByte() == 0) {
            viewClipIcash = null;
        } else {
            viewClipIcash = in.readInt();
        }
        if (in.readByte() == 0) {
            downloadClipGold = null;
        } else {
            downloadClipGold = in.readInt();
        }
        if (in.readByte() == 0) {
            downloadClipIcash = null;
        } else {
            downloadClipIcash = in.readInt();
        }
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public Video(String title, String avatar, String filemp4) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Object getArtistName() {
        return artistName;
    }

    public void setArtistName(Object artistName) {
        this.artistName = artistName;
    }

    public Object getAlbumName() {
        return albumName;
    }

    public void setAlbumName(Object albumName) {
        this.albumName = albumName;
    }

    public String getFileMp4() {
        return fileMp4;
    }

    public void setFileMp4(String fileMp4) {
        this.fileMp4 = fileMp4;
    }

    public Object getFile3gp() {
        return file3gp;
    }

    public void setFile3gp(Object file3gp) {
        this.file3gp = file3gp;
    }

    public String getFileMp4Size() {
        return fileMp4Size;
    }

    public void setFileMp4Size(String fileMp4Size) {
        this.fileMp4Size = fileMp4Size;
    }

    public String getFile3gpSize() {
        return file3gpSize;
    }

    public void setFile3gpSize(String file3gpSize) {
        this.file3gpSize = file3gpSize;
    }

    public Object getTotalDownloaded() {
        return totalDownloaded;
    }

    public void setTotalDownloaded(Object totalDownloaded) {
        this.totalDownloaded = totalDownloaded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
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

    public String getConvertStatus() {
        return convertStatus;
    }

    public void setConvertStatus(String convertStatus) {
        this.convertStatus = convertStatus;
    }

    public Object getConvertTime() {
        return convertTime;
    }

    public void setConvertTime(Object convertTime) {
        this.convertTime = convertTime;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getFbDownload() {
        return fbDownload;
    }

    public void setFbDownload(String fbDownload) {
        this.fbDownload = fbDownload;
    }

    public String getIcash() {
        return icash;
    }

    public void setIcash(String icash) {
        this.icash = icash;
    }

    public Object getFbUrl() {
        return fbUrl;
    }

    public void setFbUrl(Object fbUrl) {
        this.fbUrl = fbUrl;
    }

    public String getAwsStatus() {
        return awsStatus;
    }

    public void setAwsStatus(String awsStatus) {
        this.awsStatus = awsStatus;
    }

    public String getIcash2() {
        return icash2;
    }

    public void setIcash2(String icash2) {
        this.icash2 = icash2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public Integer getPublisherCategoryId() {
        return publisherCategoryId;
    }

    public void setPublisherCategoryId(Integer publisherCategoryId) {
        this.publisherCategoryId = publisherCategoryId;
    }

    public Integer getViewClipGold() {
        return viewClipGold;
    }

    public void setViewClipGold(Integer viewClipGold) {
        this.viewClipGold = viewClipGold;
    }

    public Integer getViewClipIcash() {
        return viewClipIcash;
    }

    public void setViewClipIcash(Integer viewClipIcash) {
        this.viewClipIcash = viewClipIcash;
    }

    public Integer getDownloadClipGold() {
        return downloadClipGold;
    }

    public void setDownloadClipGold(Integer downloadClipGold) {
        this.downloadClipGold = downloadClipGold;
    }

    public Integer getDownloadClipIcash() {
        return downloadClipIcash;
    }

    public void setDownloadClipIcash(Integer downloadClipIcash) {
        this.downloadClipIcash = downloadClipIcash;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(providerId);
        parcel.writeString(title);
        parcel.writeString(avatar);
        parcel.writeString(price);
        parcel.writeString(status);
        parcel.writeString(deleted);
        parcel.writeString(copyright);
        parcel.writeString(fileMp4);
        parcel.writeString(fileMp4Size);
        parcel.writeString(file3gpSize);
        parcel.writeString(description);
        parcel.writeString(dateCreated);
        parcel.writeString(dateModified);
        parcel.writeString(datePublished);
        parcel.writeString(userCreated);
        parcel.writeString(userModified);
        parcel.writeString(convertStatus);
        parcel.writeString(youtubeUrl);
        parcel.writeString(tags);
        parcel.writeString(downloadStatus);
        parcel.writeString(fbDownload);
        parcel.writeString(icash);
        parcel.writeString(awsStatus);
        parcel.writeString(icash2);
        parcel.writeString(price2);
        if (publisherCategoryId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(publisherCategoryId);
        }
        if (viewClipGold == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(viewClipGold);
        }
        if (viewClipIcash == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(viewClipIcash);
        }
        if (downloadClipGold == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(downloadClipGold);
        }
        if (downloadClipIcash == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(downloadClipIcash);
        }
    }
}
