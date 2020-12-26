package com.hbhb.cw.invoice.common.config;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

public class MapResultHandler implements ResultHandler {

    private final Map mappedResults = new HashMap();

    @Override
    public void handleResult(ResultContext context) {
        @SuppressWarnings("rawtypes")
        Map map = (Map) context.getResultObject();
        mappedResults.put(map.get("key"), map.get("value"));
    }

    public Map getMappedResults() {
        return mappedResults;
    }
}
