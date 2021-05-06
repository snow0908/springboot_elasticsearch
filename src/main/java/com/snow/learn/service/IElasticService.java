package com.snow.learn.service;

import com.snow.learn.esentity.DocBean;
import com.snow.learn.esentity.RecDocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

public interface IElasticService {
    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    void saveAllRec(List<RecDocBean> list);

    Iterator<DocBean> findAll();

    Page<DocBean> findByContent(String content);

    Page<DocBean> findByFirstCode(String firstCode);

    Page<DocBean> findBySecordCode(String secordCode);

    Page<DocBean> query(String key);
}
