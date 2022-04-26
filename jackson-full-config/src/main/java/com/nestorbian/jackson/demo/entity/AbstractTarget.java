package com.nestorbian.jackson.demo.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/11/23
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = HiveTarget.class),
        @JsonSubTypes.Type(value = HBaseTarget.class) })
public class AbstractTarget {
    // private String type;

    public static void main(String[] args) throws Exception {
        classTest1();
    }

    private static void test2() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();

        AbstractTarget[] array = new AbstractTarget[2];
        HiveTarget hiveTarget = new HiveTarget();
        hiveTarget.setSchema("s1");
        hiveTarget.setTable("t1");
        hiveTarget.setColumn("c1");
        array[0] = hiveTarget;

        map.put("xxx", hiveTarget);


        HBaseTarget hBaseTarget = new HBaseTarget();
        hBaseTarget.setNamespace("ns2");
        hBaseTarget.setTable("t2");
        hBaseTarget.setColumnFamily("cf2");
        hBaseTarget.setColumn("c2");
        array[1] = hBaseTarget;
        map.put("zzz", hBaseTarget);

        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化
        String statisticsStr = objectMapper.writeValueAsString(array);
        System.err.println(objectMapper.writeValueAsString(map));
        System.out.println(statisticsStr);

        // 反序列化
        AbstractTarget[] parsedStatistics = objectMapper.readValue(statisticsStr, AbstractTarget[].class);
        System.out.println(Arrays.toString(parsedStatistics));
    }

    private static void classTest1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Statistics statistics = new Statistics();
        List<AbstractTarget> targets = new ArrayList<>();
        statistics.setTargets(targets);

        HiveTarget hiveTarget = new HiveTarget();
        hiveTarget.setSchema("s1");
        hiveTarget.setTable("t1");
        hiveTarget.setColumn("c1");
        targets.add(hiveTarget);

        HBaseTarget hBaseTarget = new HBaseTarget();
        hBaseTarget.setNamespace("ns2");
        hBaseTarget.setTable("t2");
        hBaseTarget.setColumnFamily("cf2");
        hBaseTarget.setColumn("c2");
        targets.add(hBaseTarget);

        // 序列化
        String statisticsStr = objectMapper.writeValueAsString(statistics);
        System.out.println(statisticsStr);

        // 反序列化
        Statistics parsedStatistics = objectMapper.readValue(statisticsStr, Statistics.class);
        System.out.println(parsedStatistics);
    }

}

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("hive")
class HiveTarget extends AbstractTarget {
    private String schema;
    private String table;
    private String column;
    static final String TYPE = "hive";

    public HiveTarget() {
        // setType(TYPE);
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("hbase")
class HBaseTarget extends AbstractTarget {
    private String namespace;
    private String table;
    private String columnFamily;
    private String column;
    static final String TYPE = "hbase";

    public HBaseTarget() {
        // setType(TYPE);
    }
}

@Data
class Statistics {
    private List<AbstractTarget> targets;
}
