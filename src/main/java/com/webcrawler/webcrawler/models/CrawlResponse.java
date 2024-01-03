package com.webcrawler.webcrawler.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrawlResponse {

    private String crawlId;
    private CrawlStatus crawlStatus;
}
