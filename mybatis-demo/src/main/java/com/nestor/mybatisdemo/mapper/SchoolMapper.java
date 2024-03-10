package com.nestor.mybatisdemo.mapper;

import com.nestor.mybatisdemo.dto.SchoolDTO;
import com.nestor.mybatisdemo.po.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolMapper {

    List<School> listByInclude1(String name);

    List<School> listByInclude2(String name, String address);

    List<SchoolDTO> selectSchoolWithMoreResultSet(Long id);

    List<SchoolDTO> moreSelect(Long id);

    School selectByIdWithConstructor(Long id);

    School selectById(Long id);
}
