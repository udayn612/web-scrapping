package com.stackroute.service;

import com.stackroute.domain.Generic;

import java.util.Collection;

public interface GenericService {

    public void saveNode(Generic generic);

    public Collection<Generic> getDomains(String domain);

    public String createNode(String domain,String properties);



    }
