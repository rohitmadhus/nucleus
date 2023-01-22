
package com.zowie.datalibrary.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Content implements Serializable
{
    private String id;
    private String createdQuarter;
    private String publishedQuarter;
    private String title;
    private String subTitle;
    private String body;
    private String author;
    private long lastUpdatedDate;
    private long createdDate;
    private long publishedDate;
    private boolean published;
    private Web web;
    private String typeId;
    private String categoryId;
    private Notification notification;
    private WebReference webReference;
    private Image image;
    private long likes;
    private long saves;
    private Share share;
    private Ads ads;
    private String allowedAgeGroupId;
    private List<String> subContentIds = null;
    private final static long serialVersionUID = 4330992871069264557L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Content() {
    }

    /**
     * 
     * @param image
     * @param author
     * @param published
     * @param title
     * @param body
     * @param webReference
     * @param subContentIds
     * @param notification
     * @param ads
     * @param lastUpdatedDate
     * @param createdDate
     * @param allowedAgeGroupId
     * @param subTitle
     * @param saves
     * @param web
     * @param typeId
     * @param share
     * @param id
     * @param publishedDate
     * @param categoryId
     * @param likes
     */
    public Content(String id, String title, String subTitle, String body, String author, long createdDate,long lastUpdatedDate, long publishedDate, boolean published, Web web, String typeId, String categoryId, Notification notification, WebReference webReference, Image image, long likes, long saves, Share share, Ads ads, String allowedAgeGroupId, List<String> subContentIds) {
        super();
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.body = body;
        this.author = author;
        this.lastUpdatedDate = lastUpdatedDate;
        this.createdDate = createdDate;
        this.publishedDate = publishedDate;
        this.published = published;
        this.web = web;
        this.typeId = typeId;
        this.categoryId = categoryId;
        this.notification = notification;
        this.webReference = webReference;
        this.image = image;
        this.likes = likes;
        this.saves = saves;
        this.share = share;
        this.ads = ads;
        this.allowedAgeGroupId = allowedAgeGroupId;
        this.subContentIds = subContentIds;
    }

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "createdIndex"})
    public String getCreatedQuarter() { return createdQuarter; }

    public void setCreatedQuarter(String createdQuarter) { this.createdQuarter = createdQuarter; }

    @DynamoDbSecondaryPartitionKey(indexNames = {"publishedIndex" })
    public String getPublishedQuarter() { return publishedQuarter; }

    public void setPublishedQuarter(String publishedQuarter) { this.publishedQuarter = publishedQuarter; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @DynamoDbSecondarySortKey(indexNames = { "publishedIndex","categoryIndex"})
    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

    @DynamoDbSecondarySortKey(indexNames = { "createdIndex" })
    public long getCreatedDate() { return createdDate; }

    public void setCreatedDate(long createdDate) { this.createdDate = createdDate; }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Web getWeb() {
        return web;
    }

    public void setWeb(Web web) {
        this.web = web;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "categoryIndex" })
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public WebReference getWebReference() {
        return webReference;
    }

    public void setWebReference(WebReference webReference) {
        this.webReference = webReference;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getSaves() {
        return saves;
    }

    public void setSaves(long saves) {
        this.saves = saves;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
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

    public List<String> getSubContentIds() {
        return subContentIds;
    }

    public void setSubContentIds(List<String> subContentIds) {
        this.subContentIds = subContentIds;
    }

    public static enum index{

        created("createdIndex"),
        published("publishedIndex"),
        category("categoryIndex");

        public final String indexName;
        index(String indexName) {
            this.indexName = indexName;
        }
    }

}
