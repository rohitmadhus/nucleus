package com.zowie.appmanagementservice.repository;


import com.zowie.datalibrary.entity.AgeGroup;
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

import java.util.*;

@Repository
public class AgeGroupRepository {

    Logger logger = LoggerFactory.getLogger(AgeGroupRepository.class);

    private final DynamoDbEnhancedClient enhancedClient;

    private final DynamoDbTable<AgeGroup> ageGroupTable;

    AgeGroupRepository(DynamoDbEnhancedClient enhancedClient){
        this.enhancedClient = enhancedClient;
        this.ageGroupTable = enhancedClient.table("AgeGroup", TableSchema.fromBean(AgeGroup.class));
    }

    public List<AgeGroup> findAll() {
        List<AgeGroup> ageGroups = new ArrayList<>();
        try{
            Iterator<AgeGroup> results = ageGroupTable.scan().items().iterator();
            while (results.hasNext()) {
                ageGroups.add(results.next());
            }
        } catch (DynamoDbException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
        return ageGroups;
    }

}
