package com.stackroute.service;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IntentService {
    public Collection<String> getKnowledgeTerms();
    public Collection<String> getComprehensionTerms();
    public Collection<String> getApplicationTerms();
    public Collection<String> getAnalysisTerms();
    public Collection<String> getSynthesisTerms();
    public Collection<String> getEvaluationTerms();
}
