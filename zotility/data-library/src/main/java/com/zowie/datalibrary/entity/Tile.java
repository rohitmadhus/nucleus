
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Tile implements Serializable
{
    public static String VIEW_INDEX_PARTITION_KEY = "tile";


    private String id;
    private String viewIndexPartitionKey = VIEW_INDEX_PARTITION_KEY;
    private int viewOrder;
    private String title;
    private long createdDate;
    private long lastUpdatedDate;
    private long publishedDate;
    private boolean published;
    private String typeId;
    private Image image;
    private Customization customization;
    private String url;
    private String fallbackUrl;
    private Notification notification;
    private Ads ads;
    private String allowedAgeGroupId;
    private List<String> categoryIds;

    private final static long serialVersionUID = 6512337865711632710L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tile() {
    }

    /**
     * 
     * @param image
     * @param viewOrder
     * @param customization
     * @param published
     * @param title
     * @param url
     * @param notification
     * @param ads
     * @param lastUpdatedDate
     * @param createdDate
     * @param allowedAgeGroupId
     * @param fallbackUrl
     * @param typeId
     * @param id
     * @param publishedDate
     * @param categoryIds
     */
    public Tile(String id, int viewOrder,String title, long lastUpdatedDate, long createdDate, long publishedDate, boolean published, String typeId, Image image, Customization customization, String url, String fallbackUrl, Notification notification, Ads ads, String allowedAgeGroupId,List<String> categoryIds) {
        super();
        this.id = id;
        this.viewOrder = viewOrder;
        this.title = title;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.publishedDate = publishedDate;
        this.published = published;
        this.typeId = typeId;
        this.image = image;
        this.customization = customization;
        this.url = url;
        this.fallbackUrl = fallbackUrl;
        this.notification = notification;
        this.ads = ads;
        this.allowedAgeGroupId = allowedAgeGroupId;
        this.categoryIds = categoryIds;
    }

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "viewIndex" })
    public String getViewIndexPartitionKey() { return viewIndexPartitionKey; }

    public void setViewIndexPartitionKey(String viewIndexPartitionKey) { this.viewIndexPartitionKey = viewIndexPartitionKey; }

    @DynamoDbSecondarySortKey(indexNames = { "viewIndex" })
    public int getViewOrder() { return viewOrder; }

    public void setViewOrder(int viewOrder) { this.viewOrder = viewOrder; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedDate() { return createdDate; }

    public void setCreatedDate(long createdDate) { this.createdDate = createdDate; }

    public long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Customization getCustomization() {
        return customization;
    }

    public void setCustomization(Customization customization) {
        this.customization = customization;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFallbackUrl() {
        return fallbackUrl;
    }

    public void setFallbackUrl(String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public String getAllowedAgeGroupId() {
        return allowedAgeGroupId;
    }

    public void setAllowedAgeGroupId(String allowedAgeGroupId) {
        this.allowedAgeGroupId = allowedAgeGroupId;
    }

    public List<String> getCategoryIds() { return categoryIds; }

    public void setCategoryIds(List<String> categoryIds) { this.categoryIds = categoryIds; }
}
