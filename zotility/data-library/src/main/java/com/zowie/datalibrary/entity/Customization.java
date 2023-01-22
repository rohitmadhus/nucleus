
package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Customization implements Serializable
{
    private String bg1ColorHex;
    private String bg2ColorHex;
    private String textColorHex;
    private final static long serialVersionUID = -1305259643773955090L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Customization() {
    }

    /**
     * 
     * @param bg2ColorHex
     * @param textColorHex
     * @param bg1ColorHex
     */
    public Customization(String bg1ColorHex, String bg2ColorHex, String textColorHex) {
        super();
        this.bg1ColorHex = bg1ColorHex;
        this.bg2ColorHex = bg2ColorHex;
        this.textColorHex = textColorHex;
    }

    public String getBg1ColorHex() {
        return bg1ColorHex;
    }

    public void setBg1ColorHex(String bg1ColorHex) {
        this.bg1ColorHex = bg1ColorHex;
    }

    public String getBg2ColorHex() {
        return bg2ColorHex;
    }

    public void setBg2ColorHex(String bg2ColorHex) {
        this.bg2ColorHex = bg2ColorHex;
    }

    public String getTextColorHex() {
        return textColorHex;
    }

    public void setTextColorHex(String textColorHex) {
        this.textColorHex = textColorHex;
    }

}
