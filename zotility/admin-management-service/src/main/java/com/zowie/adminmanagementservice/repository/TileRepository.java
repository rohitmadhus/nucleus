package com.zowie.adminmanagementservice.repository;

import com.zowie.datalibrary.entity.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.time.Instant;
import java.util.*;

@Repository
public class TileRepository  {

    Logger logger = LoggerFactory.getLogger(TileRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<Tile> tileTable;

    TileRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.tileTable = enhancedClient.table("Tile", TableSchema.fromBean(Tile.class));
    }

    public Tile findById(String id) {
        Tile tile = null;
        try {
            Key key = Key.builder().partitionValue(id).build();
            tile = tileTable.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return tile;
    }

    public Tile create(Tile tile) {
        try {
            tile.setViewOrder(0);
            tile.setId(UUID.randomUUID().toString());
            tile.setCreatedDate(Instant.now().toEpochMilli());
            tile.setViewIndexPartitionKey(Tile.VIEW_INDEX_PARTITION_KEY);
            tileTable.putItem(tile);
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return tile;
    }

    public Tile delete(String id) {
        Tile tile = null;
        try {
            tile = tileTable.deleteItem(Key.builder().partitionValue(id).build());
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
        }
        return tile;
    }

    public Tile update(Tile tile) {
        try {
            return tileTable.updateItem((UpdateItemEnhancedRequest.Builder<Tile> requestBuilder) -> requestBuilder.item(tile));
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Tile> findAll() {
        List<Tile> tiles = new ArrayList<>();
        try{
            Iterator<Tile> results = tileTable.scan().items().iterator();
            while (results.hasNext()) {
                tiles.add(results.next());
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return tiles;
    }

    public List<Tile> find(String key,int size) {
        try{
            DynamoDbIndex<Tile> viewIndex = tileTable.index("viewIndex");
            Map<String, AttributeValue> lastKeyEvaluated = null;
            if(key !=null) {
                lastKeyEvaluated =new HashMap();
                lastKeyEvaluated.put("id", AttributeValue.builder().s(key).build());
            }

            QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(AttributeValue.builder().s(Tile.VIEW_INDEX_PARTITION_KEY).build()).build());

            QueryEnhancedRequest queryEnhancedRequest  = QueryEnhancedRequest.builder().limit(size).queryConditional(queryConditional).exclusiveStartKey(lastKeyEvaluated).scanIndexForward(true).build();
            SdkIterable<Page<Tile>> result = viewIndex.query(queryEnhancedRequest);

            if(result.stream().iterator().hasNext()){
                return result.stream().iterator().next().items();
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

}
