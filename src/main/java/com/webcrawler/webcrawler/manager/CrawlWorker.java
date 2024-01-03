package com.webcrawler.webcrawler.manager;

import com.webcrawler.webcrawler.models.CrawlResult;
import com.webcrawler.webcrawler.models.CrawlStatus;
import com.webcrawler.webcrawler.repository.CrawlRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class CrawlWorker implements Callable<CrawlResult> {

    private final String seedUrl;
    private final String crawlId;

    public CrawlWorker(String seedUrl, String crawlId) {
        this.seedUrl = seedUrl;
        this.crawlId = crawlId;
    }

    @Override
    public CrawlResult call() throws Exception {
        Set<String> visitedUrl = new HashSet<>();
        CrawlResult crawlResult = CrawlRepo.getResult(crawlId);
        int maxDepth = 4;
        fetchUrls(crawlResult, this.seedUrl, visitedUrl, maxDepth);
        crawlResult.setCrawlStatus(CrawlStatus.COMPLETED);
        return crawlResult;
    }

    private void fetchUrls(CrawlResult crawlResult, String url, Set<String> visitedUrl, int maxDepth) {

        try {
            if(maxDepth == 0) return;
            System.out.println("Fetched: " + url);
            visitedUrl.add(url);
            crawlResult.addURL(url);
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");

            for (Element link : links) {
                String crawlUrl = link.attr("href");
                if(!visitedUrl.contains(crawlUrl) && crawlUrl.startsWith("https://")){
                    fetchUrls(crawlResult, crawlUrl, visitedUrl, maxDepth-1);
                }
            }

        } catch (IOException e) {
            System.out.println("Unable to crawl url: " + this.seedUrl);
        }
    }


}
