package com.stackroute.repository;

import com.stackroute.domain.Terms;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IntentRepository extends Neo4jRepository<Terms, Integer> {
    @Query("MATCH(u:Terms) RETURN u")
    Collection<Terms> getAllTerms();

    @Query("MATCH(u:Terms) RETURN Count(*)")
    String getCount();
}
