package com.nestor.mybatisdemo.service;

import com.nestor.mybatisdemo.dto.SchoolDTO;
import com.nestor.mybatisdemo.po.School;

import java.util.List;

public interface SchoolService {

    List<School> listByInclude1(String name);

    List<School> listByInclude2(String name, String address);

    List<SchoolDTO> selectSchoolWithMoreResultSet(Long id);

    List<SchoolDTO> moreSelect(Long id);

    School selectByIdWithConstructor(Long id);

}
