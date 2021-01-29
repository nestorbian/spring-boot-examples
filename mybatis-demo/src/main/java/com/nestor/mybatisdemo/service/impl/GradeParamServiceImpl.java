package com.nestor.mybatisdemo.service.impl;

import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.mapper.GradeParamMapper;
import com.nestor.mybatisdemo.po.GradeParam;
import com.nestor.mybatisdemo.service.GradeParamService;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class GradeParamServiceImpl implements GradeParamService {

    @Autowired
    private GradeParamMapper gradeParamMapper;

    @Override
    @Transactional
    public GradeParam selectByNameAndLevel(String name, GradeLevel level) {
        // 测试一级缓存
        // gradeParamMapper.selectByNameAndLevel(name, level);
        GradeParam gradeParam = gradeParamMapper.selectByNameAndLevel(name, level);
        return gradeParam;
    }

    @Override
    public int insertOne(GradeParam gradeParam) {
        return gradeParamMapper.insertOne(gradeParam);
    }

    @Override
    public List<GradeParam> listWithFetchSize() {
        return gradeParamMapper.listWithFetchSize();
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

        if (iterator.hasNext()) {
            gradeParamList.add(iterator.next());
        }

        return gradeParamList;
    }

    @Override
    public List<GradeParam> selectByNameLike(String name) {
        return gradeParamMapper.selectByNameLike(name);
    }
}
