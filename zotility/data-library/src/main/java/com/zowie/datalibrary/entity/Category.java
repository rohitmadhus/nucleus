package com.zowie.datalibrary.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
public class Category implements Serializable {

    public static String TYPE_INDEX_PARTITION_KEY = "category";

    private String id;
    private String typeIndexPartitionKey = TYPE_INDEX_PARTITION_KEY;
    private String type;
    private String name;
    private boolean safeDelete;
    private long createdDate;

    public Category(){}

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = { "typeIndex" })
    public String getTypeIndexPartitionKey() { return typeIndexPartitionKey; }

    public void setTypeIndexPartitionKey(String typeIndexPartitionKey) { this.typeIndexPartitionKey = typeIndexPartitionKey; }

    @DynamoDbSecondarySortKey(indexNames = { "typeIndex" })
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSafeDelete() {
        return safeDelete;
    }

    public void setSafeDelete(boolean safeDelete) {
        this.safeDelete = safeDelete;
    }

    public long getCreatedDate() { return createdDate; }

    public void setCreatedDate(long createdDate) { this.createdDate = createdDate; }

}
