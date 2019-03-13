package com.stackroute.service;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Terms;

import java.util.Collection;

public interface NodeCreatorService {

    public void createConceptNode(Concept concept);

    public Collection<String> getParentNode();

    public String insertRelationship(String name,String IntentLevel);



}
