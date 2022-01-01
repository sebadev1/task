package com.leapwise.test.task.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "analysis")
public class RssAnalysis {

    @Id
    private String id;

    @OneToMany(mappedBy = "analysis")
    private Set<Topic> topics;

    @OneToMany(mappedBy = "analysis")
    private Set<RssItem> rssItems;

    @Transient
    private List<String> requestUrls;

    public RssAnalysis() {
    }

    public RssAnalysis(String id) {
        this.id = id;
    }

    public RssAnalysis(String id, List<String> requestUrls) {
        this.id = id;
        this.requestUrls = requestUrls;
    }

    public List<String> getRequestUrls() {
        return this.requestUrls;
    }

    public void setRequestUrls(List<String> requestUrls) {
        this.requestUrls = requestUrls;
    }

    public Set<RssItem> getRssItems() {
        return this.rssItems;
    }

    public void setRssItems(Set<RssItem> rssItems) {
        this.rssItems = rssItems;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Topic> getTopics() {
        return this.topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }


    
}
