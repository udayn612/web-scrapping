package com.stackroute.repository;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Generic;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public interface GenericRepository extends Neo4jRepository<Generic, Integer> {

    @Query("match(c:Generic{domain:{0}}) return c")
    Collection<Generic> getAllNodes(String domain);

    @Query("create(c:{0} {{1}})")
    String createNode(String domain,String properties);


}
