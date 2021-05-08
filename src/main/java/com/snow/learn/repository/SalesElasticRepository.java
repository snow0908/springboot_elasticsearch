package com.snow.learn.repository;

import com.snow.learn.esentity.EsSales;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SalesElasticRepository extends ElasticsearchRepository<EsSales, String> {

}
