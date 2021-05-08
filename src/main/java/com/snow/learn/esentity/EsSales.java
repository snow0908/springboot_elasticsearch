package com.snow.learn.esentity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author lx
 * @since 2021-05-08
 */
@Setter
@Getter
@Document(indexName = "sales",type = "_doc", shards = 1, replicas = 0)
public class EsSales extends Model<EsSales> {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Keyword)
    private String color;

    @Field(type = FieldType.Double)
    private BigDecimal price;

}
