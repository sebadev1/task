package com.leapwise.test.task.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.leapwise.test.task.exceptions.WrongParameterSizeException;
import com.leapwise.test.task.models.RssAnalysis;
import com.leapwise.test.task.models.RssItem;
import com.leapwise.test.task.models.Topic;
import com.leapwise.test.task.repositories.RssItemRepo;
import com.leapwise.test.task.repositories.TopicRepo;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyseController {

    @Autowired
    RssItemRepo rssItemRepository;

    @Autowired
    TopicRepo topicRepository;

    @Value("${skip.words}")
    private String skip;
    
    @PostMapping("/analyse/new")
    public String analyseRssFeeds(@RequestBody List<String> request){
        if(request.size() < 2) {
            throw new WrongParameterSizeException();
        }

        RssAnalysis analysis = new RssAnalysis(UUID.randomUUID().toString(), request);

        Set<RssItem> rssItems = getRssFeeds(analysis);
        Set<Topic> topics = parseTopics(rssItems, analysis);

        analysis.setTopics(topics);
        analysis.setRssItems(rssItems);
        topicRepository.saveAll(topics);
        rssItemRepository.saveAll(rssItems);

        //List<RssItem> l = repo.findByTitleContaining("bikini");
        return analysis.toString();
    }

    private Set<RssItem> getRssFeeds(RssAnalysis request){
        Set<RssItem> rssItems = new HashSet<>();
        for(int i = 0; i < request.getRequestUrls().size(); i++){
            try {
                XmlReader reader = new XmlReader(new URL(request.getRequestUrls().get(i)));
                SyndFeed feed = new SyndFeedInput().build(reader);
                rssItems.addAll(parseRssFeeds(feed, request));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            }
        }

        return rssItems;
    }

    private Set<RssItem> parseRssFeeds(SyndFeed rssFeed, RssAnalysis analysis) {
        Set<RssItem> rssItems = new HashSet<>();
        for (SyndEntry entry : rssFeed.getEntries()) {
            RssItem rssItem = new RssItem(entry.getTitle(), entry.getLink(), analysis);
            if(rssItems.contains(rssItem)){
                continue;
            } else {
                rssItems.add(rssItem);
            }
            
        }
        return rssItems;
    }

    private Set<Topic> parseTopics(Set<RssItem> rssItems, RssAnalysis request) {
        Map<String, Integer> topicCount = splitRssFeeds(rssItems);

        Set<Topic> topics = new HashSet<>();
        for(Map.Entry<String, Integer> entry : topicCount.entrySet()){
            topics.add(new Topic(entry.getKey(), entry.getValue(), request));
        }
        return topics;
    }

    private Map<String, Integer> splitRssFeeds(Set<RssItem> rssItems) {
        return rssItems.stream()
        .map(item -> Arrays.asList(item.getTitle().split(" ")))
        .flatMap(Collection::stream)
        .map(String::toLowerCase)
        .filter(word -> !skip.contains(word))
        .collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));
    }
}
