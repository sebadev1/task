package com.leapwise.test.task.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rssitems")
public class RssItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Lob
    @Column(columnDefinition="CLOB")
    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rss_analysis_id")
    private RssAnalysis analysis;


    public RssItem() {
    }

    public RssItem(String title, String link, RssAnalysis analysis) {
        this.title = title;
        this.link = link;
        this.analysis = analysis;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public RssAnalysis getAnalysis() {
        return this.analysis;
    }

    public void setAnalysis(RssAnalysis analysis) {
        this.analysis = analysis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RssItem)) {
            return false;
        }
        RssItem rssItem = (RssItem) o;
        return Objects.equals(title, rssItem.title);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", link='" + getLink() + "'" +
            "}";
    }

}
