// package com.nestor.springabstractcacheredis.service.impl;
//
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import com.nestor.mybatisdemo.dto.SchoolDTO;
// import com.nestor.mybatisdemo.mapper.SchoolMapper;
// import com.nestor.mybatisdemo.po.School;
// import com.nestor.mybatisdemo.service.SchoolService;
//
// /**
//  * 学校service
//  *
//  * @author : Nestor.Bian
//  * @version : V 1.0
//  * @date : 2020/4/7
//  */
// @Service
// public class SchoolServiceImpl implements SchoolService {
//
//     @Autowired
//     private SchoolMapper schoolMapper;
//
//     @Override
//     public List<School> listByInclude1(String name) {
//         return schoolMapper.listByInclude1(name);
//     }
//
//     @Override
//     public List<School> listByInclude2(String name, String address) {
//         return schoolMapper.listByInclude2(name, address);
//     }
//
//     @Override
//     public List<SchoolDTO> selectSchoolWithMoreResultSet(Long id) {
//         return schoolMapper.selectSchoolWithMoreResultSet(id);
//     }
//
//     @Override
//     public List<SchoolDTO> moreSelect(Long id) {
//         return schoolMapper.moreSelect(id);
//     }
//
//     @Override
//     public School selectByIdWithConstructor(Long id) {
//         return schoolMapper.selectByIdWithConstructor(id);
//     }
// }
