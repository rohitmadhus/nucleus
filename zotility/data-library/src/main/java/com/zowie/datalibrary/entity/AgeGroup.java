package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class AgeGroup {

    private String id;
    private String group;
    private int ageMoreThan;
    private boolean safeDelete;

    public AgeGroup(){}

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAgeMoreThan() {
        return ageMoreThan;
    }

    public void setAgeMoreThan(int ageMoreThan) {
        this.ageMoreThan = ageMoreThan;
    }

    public boolean isSafeDelete() {
        return safeDelete;
    }

    public void setSafeDelete(boolean safeDelete) {
        this.safeDelete = safeDelete;
    }
}
