package com.webcrawler.webcrawler.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CrawlResult {

    private String seedUrl;
    private String crawlId;
    private CrawlStatus crawlStatus;
    private List<String> fetchedUrls;

    public CrawlResult(String seedUrl) {
        this.seedUrl = seedUrl;
        this.crawlId = UUID.randomUUID().toString();
        this.crawlStatus = CrawlStatus.IN_PROGRESS;
        this.fetchedUrls = new ArrayList<>();
    }

    public void addURL(String url) {
        fetchedUrls.add(url);
    }
}
