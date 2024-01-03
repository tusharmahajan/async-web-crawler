package com.webcrawler.webcrawler.controller;

import com.webcrawler.webcrawler.manager.CrawlManager;
import com.webcrawler.webcrawler.models.CrawlRequest;
import com.webcrawler.webcrawler.models.CrawlResponse;
import com.webcrawler.webcrawler.models.CrawlResult;
import com.webcrawler.webcrawler.repository.CrawlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/crawl")
public class WebCrawlerController {

    @Autowired
    private CrawlManager crawlManager;

    @PostMapping("/website")
    public CrawlResponse crawlWebsite(@RequestBody CrawlRequest crawlRequest) {
        CrawlResult crawlResult = crawlManager.startCrawl(crawlRequest.getSeedUrl());
        return new CrawlResponse(crawlResult.getCrawlId(), crawlResult.getCrawlStatus());
    }

    @GetMapping("/result")
    public CrawlResult crawlWebsite(@RequestParam String crawlId) {
        return CrawlRepo.getResult(crawlId);
    }
}
