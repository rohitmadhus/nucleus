
package com.zowie.datalibrary.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Ads implements Serializable
{

    private boolean enableGoogleAdMob;
    private final static long serialVersionUID = 349816060863184853L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Ads() {
    }

    /**
     * 
     * @param enableGoogleAdMob
     */
    public Ads(boolean enableGoogleAdMob) {
        super();
        this.enableGoogleAdMob = enableGoogleAdMob;
    }

    public boolean isEnableGoogleAdMob() {
        return enableGoogleAdMob;
    }

    public void setEnableGoogleAdMob(boolean enableGoogleAdMob) {
        this.enableGoogleAdMob = enableGoogleAdMob;
    }

}
