package com.nestor.hibernatevalidation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.cache.CacheProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.jayway.jsonpath.Criteria.where;
import static com.jayway.jsonpath.Filter.filter;

/**
 * JsonPath测试
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2023/10/3
 */
public class JsonPathTest {

    @Test
    public void jsonPath01() {
        String json = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";

        // 简单示例
        List<String> authors = JsonPath.read(json, "$.store.book[*].author");
        System.out.println("authors = " + authors);


        // 预处理json方式示例：先解析 json，后计算表达式，避免重复解析
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        // 等价于 JsonPath.using(Configuration.defaultConfiguration()).parse(json)
        // 等价于 JsonPath.parse(json)
        String author0 = JsonPath.read(document, "$.store.book[0].author");
        System.out.println("author0 = " + author0);
        String author1 = JsonPath.read(document, "$.store.book[1].author");
        System.out.println("author1 = " + author1);


        // fluent风格，调用方式示例
        ReadContext ctx = JsonPath.parse(json);

        List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
        System.out.println("authorsOfBooksWithISBN = " + authorsOfBooksWithISBN);

        List<Map<String, Object>> expensiveBooks = JsonPath
                .using(Configuration.defaultConfiguration())
                .parse(json)
                .read("$.store.book[?(@.price > 10)]", List.class);
        System.out.println("expensiveBooks = " + expensiveBooks);

        //Will throw an java.lang.ClassCastException
        // List<String> list = JsonPath.parse(json).read("$.store.book[0].author");
        // System.out.println("list = " + list);

        //Works fine
        String author = JsonPath.parse(json).read("$.store.book[0].author");
        System.out.println("author = " + author);


        // 解析为POJO
        Book book = JsonPath.parse(json).read("$.store.book[0]", Book.class);
        System.out.println("book = " + book);

        TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {};

        // 需要使用Jackson or Gson based provider
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });

        List<String> titles = JsonPath.parse(json).read("$.store.book[*].title", typeRef);
        System.out.println("titles = " + titles);

        List<Map<String, Object>> books =  JsonPath.parse(json)
                .read("$.store.book[?(@.price < 10)]");
        System.out.println("books = " + books);

        Filter cheapFictionFilter = filter(
                where("category").is("fiction").and("price").lte(10D)
        );

        books = JsonPath.parse(json).read("$.store.book[?]", cheapFictionFilter);
        System.out.println("books = " + books);
    }

    @Test
    public void jsonPath02() {
        String json = "{\"date_as_long\" : 1411455611975}";

        Date date = JsonPath.parse(json).read("$['date_as_long']", Date.class);
        System.out.println("date = " + date);

        // System.out.println(Locale.getDefault());
    }

    @NoArgsConstructor
    @Data
    public static class Book {

        /**
         * category
         */
        @JsonProperty("category")
        private String category;
        /**
         * author
         */
        @JsonProperty("author")
        private String author;
        /**
         * title
         */
        @JsonProperty("title")
        private String title;
        /**
         * isbn
         */
        @JsonProperty("isbn")
        private String isbn;
        /**
         * price
         */
        @JsonProperty("price")
        private Double price;
    }

}
