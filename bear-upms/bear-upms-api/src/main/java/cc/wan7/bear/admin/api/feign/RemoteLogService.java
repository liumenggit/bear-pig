/*
 * Copyright (c) 2020 bear4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.wan7.bear.admin.api.feign;

import cc.wan7.bear.admin.api.entity.SysLog;
import cc.wan7.bear.common.core.constant.SecurityConstants;
import cc.wan7.bear.common.core.constant.ServiceNameConstants;
import cc.wan7.bear.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteLogService {

	/**
	 * 保存日志
	 * @param sysLog 日志实体
	 * @return succes、false
	 */
	@PostMapping(value = "/log", headers = SecurityConstants.HEADER_FROM_IN)
	R<Boolean> saveLog(@RequestBody SysLog sysLog);

}
