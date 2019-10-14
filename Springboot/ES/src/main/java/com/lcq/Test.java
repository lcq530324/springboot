package com.lcq;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.mapper.SourceToParse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.script.Script;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

    @org.junit.Test
    public void TestIndex() throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "MyFirstElasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("tomcat01"), 9300));
                String json = "{\n" +
                        "  \"first_name\": \"John\",\n" +
                        "  \"last_name\": \"Smith\",\n" +
                        "  \"age\": 25,\n" +
                        "  \"about\": \"I love to go rock climbing\",\n" +
                        "  \"interests\": [\n" +
                        "    \"sports\",\n" +
                        "    \"music\"\n" +
                        "  ]\n" +
                        "}";
        IndexResponse indexResponse = client.prepareIndex("megacorp", "employee")
                .setSource(json, XContentType.JSON).get();
        System.out.println("索引为："+indexResponse.getIndex());
        System.out.println("状态为：" + indexResponse.status());
        System.out.println("返回结果为：" + indexResponse.toString());

        //GET API
        GetResponse fields = client.prepareGet("megacorp", "employee", "3").get();
        System.out.println("查询id为3的员工信息：" + fields);

        //DELETE API

//        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
//                .filter(QueryBuilders.matchQuery("last_name", "Smith"))
//                .source("megacorp").get();
//        long deleted = response.getDeleted();

        //UPDate
        UpdateRequest updateRequest = new UpdateRequest("megacorp", "employee", "3")
                .script(new Script("ctx._source.last_name = \"male\""));
        client.update(updateRequest).get();
        client.close();

    }

}
