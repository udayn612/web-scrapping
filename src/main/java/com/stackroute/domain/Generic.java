package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Properties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Generic {

    @Id
    private int id;
    private String domain;
    private Map<String,String> properties;
}
