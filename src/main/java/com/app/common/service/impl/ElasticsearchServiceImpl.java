package com.app.common.service.impl;

import com.app.common.service.ElasticsearchService;
import com.app.common.utils.R;
import com.app.manager.utils.HttpUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    RestHighLevelClient highLevelClient;

    private static final String ES_URL = "http://localhost:9200/";

    @Override
    public <T> R search(String indices, String type, Map<String, String> param, int size, Class<T> clazz) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indices);
        searchRequest.types(type);

        List<MatchQueryBuilder> matchQueryList = new ArrayList<>();
        for(Map.Entry<String, String> entry : param.entrySet()){
            String name = entry.getKey();
            String text = entry.getValue();
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery(name, text);
            matchQueryList.add(matchQuery);
        }
        // 条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        QueryBuilder totalFilter = QueryBuilders.boolQuery();
        for (MatchQueryBuilder m : matchQueryList){
            ((BoolQueryBuilder) totalFilter).filter(m);
        }

        int from = 0;
        long total = 0;
        List<T> result = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>(16);
        try {
            T t = null;
            t = clazz.newInstance();

            sourceBuilder.query(totalFilter).from(from).size(size);
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            searchRequest.source(sourceBuilder);

            SearchResponse response = highLevelClient.search(searchRequest);
            SearchHit[] hits = response.getHits().getHits();

            for (SearchHit hit : hits) {
                System.out.println(hit.getSourceAsString());
                t = new Gson().fromJson(hit.getSourceAsString(), clazz);
                result.add(t);
            }
            total = response.getHits().totalHits;
            log.info("总匹配条数：[{}]",total);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("data", result);
        log.info("trace elasticsearch get resultT:[{}]",result);
        return R.ok(resultMap);
    }

    @Override
    public R insertDocs(String indices, String type, Object object) {
       String url = ES_URL + indices + "/" + type;
        return HttpUtil.doPost(url, object);
    }
}
