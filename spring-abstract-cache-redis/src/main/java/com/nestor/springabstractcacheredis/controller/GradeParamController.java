package com.nestor.springabstractcacheredis.controller;

import com.nestor.springabstractcacheredis.enums.GradeLevel;
import com.nestor.springabstractcacheredis.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.springabstractcacheredis.po.GradeParam;
import com.nestor.springabstractcacheredis.repository.GradeParamRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 年级controller
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@RestController
@Api("根据名称以及等级获取年级参数")
@Slf4j
public class GradeParamController {

	@Autowired
	private GradeParamRepository gradeParamRepository;

	@ApiOperation(value = "根据名称以及等级获取年级参数")
	@GetMapping(path = "/grade-params/{name}/{level}")
	public List<GradeParam> getByNameAndLevel(@ApiParam(name = "name") @PathVariable String name,
											 @ApiParam(name = "level") @PathVariable Integer level) {
	return gradeParamRepository.selectByNameAndLevel(name, EnumUtil.getByCode(level, GradeLevel.class));
	}

	@ApiOperation(value = "根据id获取年级参数")
	@GetMapping(path = "/grade-params/{id}")
	public GradeParam getById(@ApiParam(name = "id") @PathVariable Long id) {
		return gradeParamRepository.getById(id);
	}

	@ApiOperation(value = "根据id更新年级参数")
	@PostMapping(path = "/grade-params")
	public GradeParam updateById(@RequestBody GradeParam gradeParam) {
		return gradeParamRepository.updateById(gradeParam);
	}

	@ApiOperation(value = "根据id删除年级参数")
	@DeleteMapping(path = "/grade-params")
	public Integer deleteById(@ApiParam(name = "id") @RequestParam Long id) {
		return gradeParamRepository.deleteById(id);
	}

	@ApiOperation(value = "更新年级参数")
	@PostMapping(path = "/grade-params/all")
	public Integer updateMany(@RequestBody GradeParam gradeParam) {
		return gradeParamRepository.updateMany(gradeParam);
	}
}
