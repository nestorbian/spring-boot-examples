package com.nestor.mybatisdemo.service.impl;

import com.nestor.mybatisdemo.aop.MyLog;
import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.mapper.GradeParamMapper;
import com.nestor.mybatisdemo.po.GradeParam;
import com.nestor.mybatisdemo.service.GradeParamService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 年级service
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@Service
@Slf4j
@MyLog
public class GradeParamServiceImpl implements GradeParamService {

    @Autowired
    private GradeParamMapper gradeParamMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    @Transactional
    public GradeParam selectByNameAndLevel(String name, GradeLevel level) {
        // 测试一级缓存
        // gradeParamMapper.selectByNameAndLevel(name, level);
        GradeParam gradeParam = gradeParamMapper.selectByNameAndLevel(name, level);
        return gradeParam;
    }

    @Override
    @Transactional
    public int insertOne(GradeParam gradeParam) {
        return gradeParamMapper.insertOne(gradeParam);
    }

    @Override
    public List<GradeParam> listWithFetchSize() {
        CustomResultHandler customResultHandler = new CustomResultHandler();
        sqlSessionTemplate.select("com.nestor.mybatisdemo.mapper.GradeParamMapper.listWithFetchSize", customResultHandler);

        log.info("结果: [{}]", customResultHandler.getSize());

        // return gradeParamMapper.listWithFetchSize();
        return customResultHandler.getGradeParams();
    }

    public static class CustomResultHandler implements ResultHandler<GradeParam> {

        private int size = 0;

        @Getter
        private List<GradeParam> gradeParams = new ArrayList<>();

        @Override
        public void handleResult(ResultContext<? extends GradeParam> resultContext) {
            gradeParams.add(resultContext.getResultObject());
            // TIPS 这里累积一定数量做批量插入
            size ++;
        }

        public int getSize() {
            return size;
        }
    }

    /**
     * 流式查询(通过cursor一行行数据按需取出)
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.po.GradeParam>
     * @date : 2021/1/14 21:33
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @Transactional
    public List<GradeParam> listWithStreaming() {
        Cursor<GradeParam> cursor = gradeParamMapper.listWithStreaming();
        Iterator<GradeParam> iterator = cursor.iterator();
        List<GradeParam> gradeParamList = new LinkedList<>();

        while (iterator.hasNext()) {
            gradeParamList.add(iterator.next());
        }

        return gradeParamList;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<GradeParam> selectByNameLike(String name) {
        return gradeParamMapper.selectByNameLike(name);
    }
}
