package com.zowie.appmanagementservice.service;


import com.zowie.appmanagementservice.repository.ContentRepository;
import com.zowie.appmanagementservice.util.DateUtil;
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
