package com.stackroute.controller;

import com.stackroute.service.IntentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


@RestController
@Slf4j
public class webscrapper {

    private IntentService intentService;

    @Autowired
    webscrapper(IntentService intentService)
    {
        this.intentService=intentService;
    }


    @GetMapping("/data")
    public String getDataFromWebsite(String data)
    {
        try {
            Document doc= Jsoup.connect("https://thesaurus.plus/thesaurus/view").userAgent("mozilla/17.0").get();
            Elements temp=doc.select("li.list_block");
//            System.out.println("aaaaaaaaaaaaaaaaaaa");
//            System.out.println(temp);
            int i=0;
            for(Element movieList:temp)
            {
                    i++;
                    if(i<11)
                    {
                        System.out.println(i+" "+movieList.getElementsByTag("a").first().text());
                    }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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