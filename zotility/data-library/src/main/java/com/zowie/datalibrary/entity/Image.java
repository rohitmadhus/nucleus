
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Image implements Serializable
{

    private String url;
    private String label;
    private String shadowColorHex;
    private final static long serialVersionUID = -4086791812232734445L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Image() {
    }

    /**
     * 
     * @param shadowColorHex
     * @param label
     * @param url
     */
    public Image(String url, String label, String shadowColorHex) {
        super();
        this.url = url;
        this.label = label;
        this.shadowColorHex = shadowColorHex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShadowColorHex() {
        return shadowColorHex;
    }

    public void setShadowColorHex(String shadowColorHex) {
        this.shadowColorHex = shadowColorHex;
    }

}
