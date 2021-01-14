package com.java2nb.novel.schedule;

import com.java2nb.novel.service.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ShaoTian
 * @date 2020/12/14 11:22
 */
@Component
public class BiQuGeSchedule {

    @Autowired
    private CrawlService crawlService;

    @Scheduled(cron = "0 30 14 * * 3,7")
    public void biQuGeOpenSchedules() {
        crawlService.openOrCloseCrawl(5, (byte) 1);
    }

    @Scheduled(cron = "0 0 15 * * 3,7")
    public void biQuGeCloseSchedules() {
        crawlService.openOrCloseCrawl(5, (byte) 0);
    }

}
