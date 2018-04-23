package com.taotao.manage.service;

import com.taotao.manage.service.base.BaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;
    @Value("${IMAGE_URL}")
    public String IMAGE_URL;

}
