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
package cc.wan7.bear.apis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cc.wan7.bear.common.mybatis.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * apis 表
 *
 * @author pig code generator
 * @date 2023-02-16 15:11:40
 */
@Data
@TableName("apis")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "apis 表")
public class Apis extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description ="主键")
    private Long id;

    /**
     * 接口命名空间
     */
    @Schema(description ="接口命名空间")
    private String apiNamespace;

    /**
     * 接口服务名称
     */
    @Schema(description ="接口服务名称")
    private String apiName;

    /**
     * 接口模块名称
     */
    @Schema(description ="接口模块名称")
    private String apiModules;

    /**
     * 接口服务名称
     */
    @Schema(description ="接口服务名称")
    private String apiTitle;

    /**
     * 接口服务描述
     */
    @Schema(description ="接口服务描述")
    private String apiDesc;

    /**
     * 接口请求参数规则
     */
    @Schema(description ="接口请求参数规则")
    private String apiRequestParams;

    /**
     * 接口返回参数规则
     */
    @Schema(description ="接口返回参数规则")
    private String apiResponseParams;

    /**
     * 	接口返回示例(json)
     */
    @Schema(description ="	接口返回示例(json)")
    private String apiResponseExample;

    /**
     * 是否隐藏接口文档，0否1是
     */
    @Schema(description ="是否隐藏接口文档，0否1是")
    private String isHideDoc;

    /**
     * 请求方式
     */
    @Schema(description ="请求方式")
    private String requestMethod;

    /**
     * 	是否生成数据接口API，同时创建数据库表，1是0否
     */
    @Schema(description ="	是否生成数据接口API，同时创建数据库表，1是0否")
    private String isDataApi;

    /**
     * 完整的接口代码
     */
    @Schema(description ="完整的接口代码")
    private String apiCode;

    /**
     * 	简易的接口函数代码
     */
    @Schema(description ="	简易的接口函数代码")
    private String apiFunctionCode;

    /**
     * API开发模式，0完整模式1简易模式
     */
    @Schema(description ="API开发模式，0完整模式1简易模式")
    private String apiDevMode;

    /**
     * 数据库配置id
     */
    @Schema(description ="数据库配置id")
    private Long databaseId;

    /**
     * 数据库对应的表名字
     */
    @Schema(description ="数据库对应的表名字")
    private String databaseTableName;

    /**
     * 是否使用CURD基类接口
     */
    @Schema(description ="是否使用CURD基类接口")
    private String isCurd;


}
