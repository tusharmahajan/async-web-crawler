package com.webcrawler.webcrawler.repository;

import com.webcrawler.webcrawler.models.CrawlResult;

import java.util.HashMap;
import java.util.Map;

public class CrawlRepo {

    private static final Map<String, CrawlResult> crawlWebsite = new HashMap<>();

    private CrawlRepo(){}

    public static void storeResult(CrawlResult crawlResult) {
        crawlWebsite.put(crawlResult.getCrawlId(), crawlResult);
    }

    public static CrawlResult getResult(String crawlId){
        return crawlWebsite.get(crawlId);
    }
}
