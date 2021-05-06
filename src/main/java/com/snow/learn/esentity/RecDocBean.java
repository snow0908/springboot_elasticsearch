package com.snow.learn.esentity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName = "recems",type = "_doc", shards = 1, replicas = 0)
public class RecDocBean {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String firstCode;

    @Field(type = FieldType.Keyword)
    private String secordCode;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private String content2;

    @Field(type = FieldType.Integer)
    private Integer type;

    public RecDocBean(String id, String firstCode, String secordCode, String content, String content2, Integer type) {
        this.id = id;
        this.firstCode = firstCode;
        this.secordCode = secordCode;
        this.content = content;
        this.content2 = content2;
        this.type = type;
    }
}
