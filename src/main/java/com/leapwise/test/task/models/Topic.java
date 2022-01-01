package com.leapwise.test.task.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "topics")
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rss_analysis_id")
    private RssAnalysis analysis;

    public Topic() {
        super();
    }

    public Topic(String name, Integer count, RssAnalysis rssAnalysis) {
        this.name = name;
        this.count = count;
        this.analysis = rssAnalysis;
    }

    @Override
    public String toString() {
        return "{" +
            " topic='" + getName() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public RssAnalysis getRssAnalysis() {
        return this.analysis;
    }

    public void setRssAnalysis(RssAnalysis rssAnalysis) {
        this.analysis = rssAnalysis;
    }
    
}
