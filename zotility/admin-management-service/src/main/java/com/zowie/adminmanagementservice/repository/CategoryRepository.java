package com.zowie.adminmanagementservice.repository;


import com.zowie.datalibrary.entity.Category;
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
public class CategoryRepository {

    Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<Category> categoryTable;

    CategoryRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.categoryTable = enhancedClient.table("Category", TableSchema.fromBean(Category.class));
    }

    public Category findById(String id) {
        Category category = null;
        try {
            Key key = Key.builder().partitionValue(id).build();
            category = categoryTable.getItem((GetItemEnhancedRequest.Builder requestBuilder) -> requestBuilder.key(key));
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return category;
    }

    public Category create(Category category) {
        try {
            category.setId(UUID.randomUUID().toString());
            category.setCreatedDate(Instant.now().toEpochMilli());
            category.setTypeIndexPartitionKey(Category.TYPE_INDEX_PARTITION_KEY);
            categoryTable.putItem(category);
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
        return category;
    }

    public Category delete(String id) {
        Category category = null;
        try {
            category = categoryTable.deleteItem(Key.builder().partitionValue(id).build());
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
        }
        return category;
    }

    public Category update(Category category) {
        try {
            return categoryTable.updateItem((UpdateItemEnhancedRequest.Builder<Category> requestBuilder) -> requestBuilder.item(category));
        }
        catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try{
            Iterator<Category> results = categoryTable.scan().items().iterator();
            while (results.hasNext()) {
                categories.add(results.next());
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return categories;
    }

    public List<Category> find(String key,int size) {
        try{
            DynamoDbIndex<Category> index = categoryTable.index("typeIndex");
            Map<String, AttributeValue> lastKeyEvaluated = null;
            if(key !=null) {
                lastKeyEvaluated =new HashMap();
                lastKeyEvaluated.put("id", AttributeValue.builder().s(key).build());
            }

            QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(AttributeValue.builder().s(Category.TYPE_INDEX_PARTITION_KEY).build()).build());

            QueryEnhancedRequest queryEnhancedRequest  = QueryEnhancedRequest.builder().limit(size).queryConditional(queryConditional).exclusiveStartKey(lastKeyEvaluated).scanIndexForward(true).build();
            SdkIterable<Page<Category>> result = index .query(queryEnhancedRequest);

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