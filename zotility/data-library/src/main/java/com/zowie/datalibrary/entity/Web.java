
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Web implements Serializable
{
    private boolean published;
    private long publishedDate;
    private final static long serialVersionUID = 471723188406870010L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Web() {
    }

    /**
     * 
     * @param published
     * @param publishedDate
     */
    public Web(boolean published, long publishedDate) {
        super();
        this.published = published;
        this.publishedDate = publishedDate;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

}
