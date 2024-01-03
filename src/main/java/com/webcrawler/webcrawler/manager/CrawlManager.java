package com.webcrawler.webcrawler.manager;

import com.webcrawler.webcrawler.models.CrawlResult;
import com.webcrawler.webcrawler.repository.CrawlRepo;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CrawlManager {

    public CrawlResult startCrawl(String seedUrl) {
        CrawlResult crawlResult = new CrawlResult(seedUrl);
        CrawlRepo.storeResult(crawlResult);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(new CrawlWorker(seedUrl, crawlResult.getCrawlId()));
        executorService.shutdown();
        return crawlResult;
    }
}
