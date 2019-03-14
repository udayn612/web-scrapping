package com.stackroute.controller;

import com.stackroute.domain.Concept;
import com.stackroute.domain.Terms;
import com.stackroute.service.IntentService;
import com.stackroute.service.NodeCreatorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


@RestController
@Slf4j
@CrossOrigin("*")
public class webscrapper {

    private IntentService intentService;
    private NodeCreatorService nodeCreatorService;

    @Autowired
    webscrapper(IntentService intentService,NodeCreatorService nodeCreatorService)

    {
        this.intentService=intentService;
        this.nodeCreatorService=nodeCreatorService;
    }


    @GetMapping("/{data}")
    public ArrayList<String> getDataFromWebsite(@PathVariable("data") String data)
    {
        ArrayList<String> knowledgeTerms = new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://thesaurus.plus/thesaurus/"+data+"/").userAgent("mozilla/17.0").get();
            Elements temp=doc.select("li.list_block");

            int i=0;
            for(Element movieList:temp)
            {
                    i++;
                    if(i<11)
                    {
                        knowledgeTerms.add(movieList.getElementsByTag("a").first().text());
                    }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return knowledgeTerms;
    }

    @GetMapping("/getcount")
    public String getTermsCount()
    {
        return intentService.getCount();
    }

    @GetMapping("insertTerm/{intent}/{synonym}/{score}")
    public ResponseEntity<String> saveTermsToDb(@PathVariable("intent") String intent,
                              @PathVariable("synonym") String Synonym,
                              @PathVariable("score")  String score)
    {

        String intentLevel = intent.substring(0,1).toUpperCase() + intent.substring(1).toLowerCase();
        System.out.println("Inside my function");
        String parent_id="SPRING:1";


        if(intentLevel.equalsIgnoreCase("knowledge"))
        {
            parent_id="SPRING:2";
        }
        else if(intentLevel.equalsIgnoreCase("comprehension"))
        {
            parent_id="SPRING:3";
        }
        else if(intentLevel.equalsIgnoreCase("Analysis"))
        {
            parent_id="SPRING:4";
        }
        else if(intentLevel.equalsIgnoreCase("Application"))
        {
            parent_id="SPRING:5";
        }
        else if(intentLevel.equalsIgnoreCase("Synthesis"))
        {
            parent_id="SPRING:6";
        }
        else if(intentLevel.equalsIgnoreCase("Evaluation"))
        {
            parent_id="SPRING:7";
        }

        String Id=intentService.getCount();

        Terms term1=new Terms((Integer.parseInt(Id)+1),Synonym,parent_id,intentLevel,"term","indicatorOf",score);
        intentService.createTermNode(term1);
        nodeCreatorService.insertRelationship(Synonym,intentLevel);

        System.out.println(term1);
        return  new ResponseEntity<String>("Inserted term Successfully", HttpStatus.OK);
    }


    @GetMapping("createConcept/{parentName}/{name}/")
    public ResponseEntity<String> saveConceptsToDb(@PathVariable("parentName") String parentName,
                                                @PathVariable("name") String name)
    {
        Concept getPerticularConcept=nodeCreatorService.getPerticularNode(parentName);


        String nodeId=nodeCreatorService.getConceptNodeCount();

        String parentId=getPerticularConcept.getParentId();
        Concept concept=new Concept(
            Integer.parseInt(nodeId),name,parentId,"subconcept of","concept",parentName,"concept"

        );
        nodeCreatorService.createConceptNode(concept);
        nodeCreatorService.insertConceptRelationship(name,parentName);
        return  new ResponseEntity<String>("Inserted Concept Successfully", HttpStatus.OK);
    }



    @GetMapping("getIntentTerms/{IntentLevel}")
    public Collection<String> getIntentTerms(@PathVariable("IntentLevel") String IntentLevel)
    {
        if(IntentLevel.equalsIgnoreCase("knowledge"))
        {
               return intentService.getKnowledgeTerms();
        }
        else if(IntentLevel.equalsIgnoreCase("Comprehension"))
        {
                return intentService.getComprehensionTerms();
        }
        else if(IntentLevel.equalsIgnoreCase("Analysis"))
        {
               return intentService.getAnalysisTerms();
        }
        else if(IntentLevel.equalsIgnoreCase("Application"))
        {
               return intentService.getApplicationTerms();
        }
        else if(IntentLevel.equalsIgnoreCase("synthesis"))
        {
                return intentService.getSynthesisTerms();
        }
        else if(IntentLevel.equalsIgnoreCase("Evaluation"))
        {
                return intentService.getEvaluationTerms();
        }
        return intentService.getEvaluationTerms();
    }

}
