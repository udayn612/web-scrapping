package com.stackroute.service;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Terms;
import com.stackroute.repository.ConceptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NodeCreatorServiceImpl implements NodeCreatorService {

    private ConceptRepository conceptRepository;

    @Autowired
    public NodeCreatorServiceImpl(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }


    public void createConceptNode(Concept concept) {
            conceptRepository.save(concept);
    }




    public ArrayList<String> getParentNode() {
        ArrayList<Concept> concepts = new ArrayList<>(conceptRepository.getAllNodes());
        ArrayList<String> conceptNames = new ArrayList<>();
        for (int i = 0; i < concepts.size(); i++) {
            if(!conceptNames.contains(concepts.get(i).getContext()))
            {
                conceptNames.add(concepts.get(i).getContext());
            }

        }
        return conceptNames;
    }


}
