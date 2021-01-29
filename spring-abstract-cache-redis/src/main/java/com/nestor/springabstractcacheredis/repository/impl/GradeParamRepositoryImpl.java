package com.nestor.springabstractcacheredis.repository.impl;

import com.nestor.springabstractcacheredis.po.GradeParamExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.nestor.springabstractcacheredis.enums.GradeLevel;
import com.nestor.springabstractcacheredis.mapper.GradeParamMapper;
import com.nestor.springabstractcacheredis.po.GradeParam;
import com.nestor.springabstractcacheredis.repository.GradeParamRepository;

import java.util.List;

/**
 * 年级service
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@Repository
@CacheConfig(cacheNames = "GradeParam")
public class GradeParamRepositoryImpl implements GradeParamRepository {

    @Autowired
    private GradeParamMapper gradeParamMapper;

    /**
     * 根据id缓存
     *
     * @param id
     * @return com.nestor.springabstractcacheredis.po.GradeParam
     * @date : 2020/11/11 22:48
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @Cacheable
    // @Cacheable(key = "#id")
    // @Cacheable(key = "#root.methodName")
    // @Cacheable(key = "#root.method.getName()")
    // @Cacheable(key = "#root.method.name")
    // @Cacheable(key = "#root.target")
    // @Cacheable(key = "#root.targetClass")
    // @Cacheable(key = "#root.args[0]")
    // @Cacheable(key = "#root.caches[0].name")
    public GradeParam getById(Long id) {
        return gradeParamMapper.selectByPrimaryKey(id);
    }

    /**
     * 默认key策略SimpleKeyGenerator
     *
     * @param name
     * @param level
     * @return java.util.List<com.nestor.springabstractcacheredis.po.GradeParam>
     * @date : 2021/1/14 22:59
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @Cacheable
    public List<GradeParam> selectByNameAndLevel(String name, GradeLevel level) {
        GradeParamExample gradeParamExample = new GradeParamExample();
        gradeParamExample.createCriteria().andNameEqualTo(name).andLevelEqualTo(level);
        return gradeParamMapper.selectByExample(gradeParamExample);
    }

    /**
     * 根据id key更新缓存
     *
     * @param gradeParam
     * @return com.nestor.springabstractcacheredis.po.GradeParam
     * @date : 2020/11/11 23:18
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @CachePut(key = "#gradeParam.id")
    public GradeParam updateById(GradeParam gradeParam) {
        int i = gradeParamMapper.updateByPrimaryKeySelective(gradeParam);
        return gradeParam;
    }

    /**
     * 根据id key删除缓存
     *
     * @param id
     * @return int
     * @date : 2020/11/11 23:18
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @CacheEvict(key = "#id")
    public int deleteById(Long id) {
        return gradeParamMapper.deleteByPrimaryKey(id);
    }

    /**
     * 清空当前cacheName下的所有缓存
     *
     * @param gradeParam
     * @return java.lang.Integer
     * @date : 2020/11/11 23:19
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @CacheEvict(allEntries = true)
    public Integer updateMany(GradeParam gradeParam) {
        GradeParamExample gradeParamExample = new GradeParamExample();
        gradeParamExample.createCriteria().andLevelEqualTo(GradeLevel.SECOND);
        return gradeParamMapper.updateByExampleSelective(gradeParam, gradeParamExample);
    }
}
