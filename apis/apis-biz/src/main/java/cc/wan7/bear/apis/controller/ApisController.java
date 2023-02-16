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

package cc.wan7.bear.apis.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cc.wan7.bear.common.core.util.R;
import cc.wan7.bear.common.log.annotation.SysLog;
import cc.wan7.bear.apis.entity.Apis;
import cc.wan7.bear.apis.service.ApisService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


/**
 * apis 表
 *
 * @author pig code generator
 * @date 2023-02-16 15:11:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/apis" )
@Tag(name = "apis 表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ApisController {

    private final  ApisService apisService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param apis apis 表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('apis_apis_get')" )
    public R getApisPage(Page page, Apis apis) {
        return R.ok(apisService.page(page, Wrappers.query(apis)));
    }


    /**
     * 通过id查询apis 表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('apis_apis_get')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(apisService.getById(id));
    }

    /**
     * 新增apis 表
     * @param apis apis 表
     * @return R
     */
    @Operation(summary = "新增apis 表", description = "新增apis 表")
    @SysLog("新增apis 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('apis_apis_add')" )
    public R save(@RequestBody Apis apis) {
        return R.ok(apisService.save(apis));
    }

    /**
     * 修改apis 表
     * @param apis apis 表
     * @return R
     */
    @Operation(summary = "修改apis 表", description = "修改apis 表")
    @SysLog("修改apis 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('apis_apis_edit')" )
    public R updateById(@RequestBody Apis apis) {
        return R.ok(apisService.updateById(apis));
    }

    /**
     * 通过id删除apis 表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除apis 表", description = "通过id删除apis 表")
    @SysLog("通过id删除apis 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('apis_apis_del')" )
    public R removeById(@PathVariable Long id) {
        return R.ok(apisService.removeById(id));
    }

}
