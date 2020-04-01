package com.app.common.service;

import com.app.common.utils.R;

import java.util.Map;

public interface ElasticsearchService {

    /**
     * 按索引查找
     * @param indices  index
     * @param type  type
     * @param param  参数
     * @param size  条数
     * @return
     */
    <T> R search(String indices, String type, Map<String, String> param, int size, Class<T> clazz);

    /**
     * 插入doc
     * @param indices
     * @param type
     * @param object
     * @return
     */
    R insertDocs(String indices, String type, Object object);
}
