/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the bear4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package cc.wan7.bear.demo.controller;

import cc.wan7.bear.demo.mapper.DemoMapper;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cc.wan7.bear.common.core.util.R;
import cc.wan7.bear.common.log.annotation.SysLog;
import cc.wan7.bear.demo.entity.Demo;
import cc.wan7.bear.demo.service.DemoService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * bear_demo 表
 *
 * @author pig code generator
 * @date 2023-02-13 09:28:01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
@Tag(name = "bear_demo 表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class DemoController {

	private final DemoService demoService;

	@RequestMapping("/de/{cateId}")
	public R saveDemo(@RequestParam Map<String, Object> params, @PathVariable("cateId") Long cateId) {
		PageUtil page = demoService.queryPage(params, cateId);
		return R.ok(page);
	}


	@GetMapping("/test")
	public R saveDemo(@RequestBody Demo demo) {
		Demo saveDemo = demoService.saveDemo(demo);
		return R.ok(saveDemo);
	}

	/**
	 * 分页查询
	 *
	 * @param page 分页对象
	 * @param demo bear_demo 表
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	@SysLog("查询bear_demo 表page")
	@PreAuthorize("@pms.hasPermission('demo_demo_get')")
	public R getDemoPage(Page page, Demo demo) {
		return R.ok(demoService.page(page, Wrappers.query(demo)));
	}


	/**
	 * 通过id查询bear_demo 表
	 *
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('demo_demo_get')")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(demoService.getById(id));
	}

	/**
	 * 新增bear_demo 表
	 *
	 * @param demo bear_demo 表
	 * @return R
	 */
	@Operation(summary = "新增bear_demo 表", description = "新增bear_demo 表")
	@SysLog("新增bear_demo 表")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('demo_demo_add')")
	public R save(@RequestBody Demo demo) {
		return R.ok(demoService.save(demo));
	}

	/**
	 * 修改bear_demo 表
	 *
	 * @param demo bear_demo 表
	 * @return R
	 */
	@Operation(summary = "修改bear_demo 表", description = "修改bear_demo 表")
	@SysLog("修改bear_demo 表")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('demo_demo_edit')")
	public R updateById(@RequestBody Demo demo) {
		return R.ok(demoService.updateById(demo));
	}

	/**
	 * 通过id删除bear_demo 表
	 *
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除bear_demo 表", description = "通过id删除bear_demo 表")
	@SysLog("通过id删除bear_demo 表")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('demo_demo_del')")
	public R removeById(@PathVariable Long id) {
		return R.ok(demoService.removeById(id));
	}

}
