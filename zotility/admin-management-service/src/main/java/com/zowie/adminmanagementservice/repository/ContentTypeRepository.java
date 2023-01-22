package com.zowie.adminmanagementservice.repository;

import com.zowie.datalibrary.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ContentTypeRepository  {
    Logger logger = LoggerFactory.getLogger(ContentTypeRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<ContentType> contentTypeTable;

    ContentTypeRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.contentTypeTable = enhancedClient.table("ContentType", TableSchema.fromBean(ContentType.class));
    }

    public ContentType findById(String id) {
        ContentType contentType = null;
        try {
            Key key = Key.builder().partitionValue(id).build();
            contentType = contentTypeTable.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return contentType;
    }

    public ContentType create(ContentType contentType) {
        try {
            contentType.setId(UUID.randomUUID().toString());
            contentTypeTable.putItem(contentType);
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return contentType;
    }

    public ContentType delete(String id) {
        ContentType contentType = null;
        try {
            contentType = contentTypeTable.deleteItem(Key.builder().partitionValue(id).build());
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
        }
        return contentType;
    }

    public ContentType update(ContentType contentType) {
        try {
            return contentTypeTable.updateItem((UpdateItemEnhancedRequest.Builder<ContentType> requestBuilder) -> requestBuilder.item(contentType));
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<ContentType> findAll() {
        List<ContentType> contentTypes = new ArrayList<>();
        try{
            Iterator<ContentType> results = contentTypeTable.scan().items().iterator();
            while (results.hasNext()) {
                contentTypes.add(results.next());
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return contentTypes;
    }

}
