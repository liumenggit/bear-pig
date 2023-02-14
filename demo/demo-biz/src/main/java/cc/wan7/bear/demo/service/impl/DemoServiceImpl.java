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
package cc.wan7.bear.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cc.wan7.bear.demo.entity.Demo;
import cc.wan7.bear.demo.mapper.DemoMapper;
import cc.wan7.bear.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * bear_demo 表
 *
 * @author pig code generator
 * @date 2023-02-13 09:28:01
 */
@Service
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

	@Resource
	DemoMapper demoMapper;

	@Override
	public Demo saveDemo(Demo vo) {
		Demo demo = new Demo();
		BeanUtil.copyProperties(vo, demo);
		demo.setUsername("test");
		demoMapper.insert(demo);
//		baseMapper.insert(demo);
		return demo;
	}

	@Override
	public PageUtil queryPage(Map<String, Object> params, Long cateId) {
		return null;
	}

}
