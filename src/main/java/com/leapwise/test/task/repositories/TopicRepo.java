package com.leapwise.test.task.repositories;

import java.util.List;
import java.util.Optional;

import com.leapwise.test.task.models.RssAnalysis;
import com.leapwise.test.task.models.Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepo extends JpaRepository<Topic, Long> {
    
    Optional<List<Topic>> findTop3ByAnalysisOrderByCountDesc(RssAnalysis id);
}
