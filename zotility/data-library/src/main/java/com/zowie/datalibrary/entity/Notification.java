
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Notification implements Serializable
{
    private boolean allowed;
    private long scheduledDate;
    private final static long serialVersionUID = 4793767174277094467L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Notification() {
    }

    /**
     * 
     * @param allowed
     * @param scheduledDate
     */
    public Notification(boolean allowed, long scheduledDate) {
        super();
        this.allowed = allowed;
        this.scheduledDate = scheduledDate;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public long getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(long scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

}
