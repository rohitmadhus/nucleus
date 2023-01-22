package com.zowie.adminmanagementservice.repository;


import com.zowie.datalibrary.entity.TileType;
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
public class TileTypeRepository  {

    Logger logger = LoggerFactory.getLogger(TileTypeRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<TileType> tileTypeTable;

    TileTypeRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.tileTypeTable = enhancedClient.table("TileType", TableSchema.fromBean(TileType.class));
    }

    public TileType findById(String id) {
        TileType tileType = null;
        try {
            Key key = Key.builder().partitionValue(id).build();
            tileType = tileTypeTable.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return tileType;
    }

    public TileType create(TileType tileType) {
        try {
            tileType.setId(UUID.randomUUID().toString());
            tileTypeTable.putItem(tileType);
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return tileType;
    }

    public TileType delete(String id) {
        TileType tileType = null;
        try {
            tileType = tileTypeTable.deleteItem(Key.builder().partitionValue(id).build());
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
        }
        return tileType;
    }

    public TileType update(TileType tileType) {
        try {
            return tileTypeTable.updateItem((UpdateItemEnhancedRequest.Builder<TileType> requestBuilder) -> requestBuilder.item(tileType));
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<TileType> findAll() {
        List<TileType> tileTypes = new ArrayList<>();
        try{
            Iterator<TileType> results = tileTypeTable.scan().items().iterator();
            while (results.hasNext()) {
                tileTypes.add(results.next());
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return tileTypes;
    }

}
