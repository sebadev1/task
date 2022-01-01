package com.leapwise.test.task.repositories;

import java.util.List;

import com.leapwise.test.task.models.RssItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssItemRepo extends JpaRepository<RssItem, Long>{

    List<RssItem> findTop3ByTitleIgnoreCaseContaining(String topic);
    
}
