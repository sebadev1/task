package com.leapwise.test.task.models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrequencyResponse {
    
    @JsonProperty(value = "topics")
    private Map<String, Integer> topics;

    @JsonProperty(value = "items")
    private Map<String, String> rssItems;


    public FrequencyResponse() {
    }

    public FrequencyResponse(Map<String, Integer> topics, Map<String, String> rssItems) {
        this.topics = topics;
        this.rssItems = rssItems;
    }

    public Map<String, Integer> getTopics() {
        return this.topics;
    }

    public void setTopics(Map<String, Integer> topics) {
        this.topics = topics;
    }

    public Map<String, String> getRssItems() {
        return this.rssItems;
    }

    public void setRssItems(Map<String, String> rssItems) {
        this.rssItems = rssItems;
    }

}
