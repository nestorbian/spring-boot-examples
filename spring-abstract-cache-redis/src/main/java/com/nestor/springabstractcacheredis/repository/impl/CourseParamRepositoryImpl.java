package com.nestor.springabstractcacheredis.repository.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.nestor.springabstractcacheredis.mapper.CourseParamMapper;
import com.nestor.springabstractcacheredis.po.CourseParam;
import com.nestor.springabstractcacheredis.po.CourseParamExample;
import com.nestor.springabstractcacheredis.repository.CourseParamRepository;

import java.sql.PreparedStatement;
import java.util.Optional;

/**
 * 课程参数数据操作类
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2021/1/13
 */
@Repository
@CacheConfig(cacheNames = {"CourseParam"})
public class CourseParamRepositoryImpl implements CourseParamRepository {
    @Autowired
    private CourseParamMapper courseParamMapper;

    /**
     * 根据id缓存
     *
     * @param id
     * @return com.nestor.springabstractcacheredis.po.CourseParam
     * @date : 2020/11/11 22:48
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    // @Cacheable
    @Cacheable(key = "#id")
    // @Cacheable(key = "#root.methodName")
    // @Cacheable(key = "#root.method.getName()")
    // @Cacheable(key = "#root.method.name")
    // @Cacheable(key = "#root.target")
    // @Cacheable(key = "#root.targetClass")
    // @Cacheable(key = "#root.args[0]")
    // @Cacheable(key = "#root.caches[0].name")
    // @Cacheable(key = "getMethodName() + '(' + T(org.apache.commons.lang3.StringUtils).join(getArgs(), ',') + ')'")
    // @Cacheable(key = "#id", condition = "#id == 1")
    // @Cacheable(key = "#id", unless = "#result.id == 1")
    // @Cacheable(keyGenerator = "customKeyGenerator")
    public CourseParam getById(Long id) {
        return courseParamMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id key更新缓存,可以结合java 8 Optional使用
     *
     * @param courseParam
     * @return com.nestor.springabstractcacheredis.po.CourseParam
     * @date : 2020/11/11 23:18
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @CachePut(key = "#courseParam.id")
    public CourseParam updateById(CourseParam courseParam) {
        int i = courseParamMapper.updateByPrimaryKeySelective(courseParam);
        return courseParam;
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
        return courseParamMapper.deleteByPrimaryKey(id);
    }

    /**
     * 清空当前cacheName下的所有缓存
     *
     * @param courseParam
     * @return java.lang.Integer
     * @date : 2020/11/11 23:19
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @Override
    @CacheEvict(allEntries = true)
    public Integer updateMany(CourseParam courseParam) {
        CourseParamExample courseParamExample = new CourseParamExample();
        courseParamExample.createCriteria().andInstructorEqualTo(courseParam.getInstructor());
        return courseParamMapper.updateByExampleSelective(courseParam, courseParamExample);
    }
}
