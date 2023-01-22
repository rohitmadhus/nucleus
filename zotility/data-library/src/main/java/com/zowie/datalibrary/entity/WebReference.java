
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class WebReference implements Serializable
{
    private String url;
    private String label;
    private final static long serialVersionUID = 1076574603544595795L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WebReference() {
    }

    /**
     * 
     * @param label
     * @param url
     */
    public WebReference(String url, String label) {
        super();
        this.url = url;
        this.label = label;
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

}
