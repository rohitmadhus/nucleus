package com.zowie.appmanagementservice.repository;


import com.zowie.datalibrary.entity.TileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
