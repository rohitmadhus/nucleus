package com.zowie.appmanagementservice.repository;


import com.zowie.datalibrary.entity.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.*;

@Repository
public class ContentRepository  {


    Logger logger = LoggerFactory.getLogger(ContentRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<Content> contentTable;

    ContentRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.contentTable = enhancedClient.table("Content", TableSchema.fromBean(Content.class));
    }

    public Content findById(String id) {
        Content content = null;
        try {
            Key key = Key.builder().partitionValue(id).build();
            content = contentTable.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return content;
    }

    public List<Content> find(String key,int size,String indexName,String partitionKey) {
        try{
            DynamoDbIndex<Content> index = contentTable.index(indexName);
            Map<String, AttributeValue> lastKeyEvaluated = null;
            if(key !=null) {
                lastKeyEvaluated =new HashMap();
                lastKeyEvaluated.put("id", AttributeValue.builder().s(key).build());
            }

            QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(AttributeValue.builder().s(partitionKey).build()).build());

            QueryEnhancedRequest queryEnhancedRequest  = QueryEnhancedRequest.builder().limit(size).queryConditional(queryConditional).exclusiveStartKey(lastKeyEvaluated).scanIndexForward(true).build();
            SdkIterable<Page<Content>> result = index .query(queryEnhancedRequest);

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
