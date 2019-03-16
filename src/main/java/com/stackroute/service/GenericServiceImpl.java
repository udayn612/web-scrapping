package com.stackroute.service;

import com.stackroute.domain.Generic;
import com.stackroute.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GenericServiceImpl implements GenericService {

    private GenericRepository genericRepository;

    @Autowired
    public GenericServiceImpl(GenericRepository genericRepository) {
        this.genericRepository = genericRepository;
    }

    public void saveNode(Generic generic){

        System.out.println("aaaaaaaaaaaaa");

        genericRepository.save(generic);

    }

    public Collection<Generic> getDomains(String domain){

       return genericRepository.getAllNodes(domain);
    }

    public String createNode(String domain,String properties){

        return genericRepository.createNode(domain,properties);
    }

}
