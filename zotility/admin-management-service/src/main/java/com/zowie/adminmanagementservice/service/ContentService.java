package com.zowie.adminmanagementservice.service;


import com.zowie.adminmanagementservice.repository.ContentRepository;
import com.zowie.adminmanagementservice.util.DateUtil;
import com.zowie.datalibrary.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;
import java.util.*;

@Service
public class ContentService {

    final static int MAX_PAGE_SIZE = 100;

    @Autowired
    ContentRepository contentRepository;

    public Content create(Content content){
        content.setCreatedDate(Instant.now().toEpochMilli());
        content.setId(UUID.randomUUID().toString());
        content.setCreatedQuarter(DateUtil.getQuarter(Date.from(Instant.now())));
        content.setLastUpdatedDate(content.getCreatedDate());
        return contentRepository.create(content);
    }

    public Content publish(String id){
        Content content = contentRepository.findById(id);
        content.setLastUpdatedDate(Instant.now().toEpochMilli());
        content.setPublishedDate(content.getLastUpdatedDate());
        content.setPublishedQuarter(DateUtil.getQuarter(Date.from(Instant.now())));
        content.setPublished(true);
        return contentRepository.update(content);
    }

    public Content update(Content content){
        indexSanity(content);
        content.setLastUpdatedDate(Instant.now().toEpochMilli());
        return contentRepository.update(content);
    }

    /**
     * Normal pagination where we can apply filter
     * 1. Published ( NULL means both published and unpublished )
     * 2. List of categories
     *
     * use globalIndex
     **/

    public List<Content> find(String lastKey,int size,int index,String partition){
        int checkOldData = 0;
        if(size > MAX_PAGE_SIZE)
            size = MAX_PAGE_SIZE;
        Map<String, AttributeValue> lastKeyEvaluated = null;
        if(lastKey !=null) {
            lastKeyEvaluated =new HashMap();
            lastKeyEvaluated.put("id", AttributeValue.builder().s(lastKey).build());
        }
        if (partition == null || partition.equalsIgnoreCase("unknown")){
            partition = DateUtil.getQuarter(new Date(Instant.now().toEpochMilli()));
        }
        if(index >= 0 && index < Content.index.values().length){
            List<Content> contents =  contentRepository.find(lastKey,size,Content.index.values()[index].indexName,partition);
            if (contents == null || contents.size() < size) {
                String quarter = partition;
                while (checkOldData <= 5) {
                    quarter = DateUtil.getPreviousQuarter(quarter);
                    checkOldData++;
                    List<Content> prevData = contentRepository.find(lastKey, size, Content.index.values()[index].indexName, quarter);
                    if (prevData != null) {
                        contents.addAll(prevData);
                    }
                }
            }
            return contents;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Get with specific category
     *
     * use categoryIndex
     **/

//    public List<Content> getContentsByCategory(String lastKey, Boolean published, int size, String category){
//        if(size > MAX_PAGE_SIZE)
//            size = MAX_PAGE_SIZE;
//        Map<String, AttributeValue> lastKeyEvaluated = null;
//        if(lastKey !=null) {
//            lastKeyEvaluated =new HashMap();
//            lastKeyEvaluated.put("id", AttributeValue.builder().s(lastKey).build());
//        }
//        Map<String, String> expressionNames = new HashMap<>();
//        Map<String, AttributeValue> expressionValues = new HashMap<>();
//        String expressionString = "";
//        if(published != null){
//            expressionString = ":published = #published";
//            expressionNames.put("#published", "published");
//            expressionValues.put(":published",AttributeValue.builder().bool(published).build());
//        }
//        Expression expression = Expression.builder().expressionValues(expressionValues).expressionNames(expressionNames).expression(expressionString).build();
//        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(category).build());
//        return contentRepository.findByCategoryId(lastKeyEvaluated,size,expression,queryConditional);
//    }


    private void indexSanity(Content content){
        Content c = contentRepository.findById(content.getId());
        if(c != null){
            content.setPublishedDate(c.getPublishedDate());
            content.setPublishedQuarter(c.getPublishedQuarter());

            content.setCreatedDate(c.getCreatedDate());
            content.setCreatedQuarter(c.getCreatedQuarter());
        }
    }

}
