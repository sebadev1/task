package com.leapwise.test.task.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.leapwise.test.task.models.FrequencyResponse;
import com.leapwise.test.task.models.RssAnalysis;
import com.leapwise.test.task.models.RssItem;
import com.leapwise.test.task.models.Topic;
import com.leapwise.test.task.repositories.RssItemRepo;
import com.leapwise.test.task.repositories.TopicRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrequencyController {
    
    @Autowired
    TopicRepo topicRepo;

    @Autowired
    RssItemRepo itemRepo;

    @GetMapping("/frequency/{id}")
    public FrequencyResponse getTopicsAndNews(@PathVariable String id) {
        Optional<List<Topic>> topics = topicRepo.findTop3ByAnalysisOrderByCountDesc(new RssAnalysis(id));
        List<Topic> topicList = topics.get();
        if(topicList.size() == 0) {
            throw new NoSuchElementException();
        }
        List<RssItem> items = itemRepo.findTop3ByTitleIgnoreCaseContaining(" " + topicList.get(0).getName() + " ");

        Map<String, Integer> topicsMap = convertTopicList(topicList);
        Map<String, String> itemsMap = convertItemList(items);

        FrequencyResponse response = new FrequencyResponse(topicsMap, itemsMap);
        return response;
    }

    public Map<String, Integer> convertTopicList(List<Topic> topics) {
        Map<String, Integer> map = topics.stream()
            .collect(Collectors.toMap(Topic::getName, Topic::getCount));
        return map;
    }

    public Map<String, String> convertItemList(List<RssItem> rssItems) {
        Map<String, String> map = rssItems.stream()
            .collect(Collectors.toMap(RssItem::getTitle, RssItem::getLink));
        return map;
    }
}
