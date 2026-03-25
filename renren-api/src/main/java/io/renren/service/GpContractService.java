/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service;

import io.renren.common.page.PageData;
import io.renren.common.service.CrudService;
import io.renren.dto.GpContractDTO;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;

import java.util.List;
import java.util.Map;

/**
 * 股票合约服务接口
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface GpContractService extends CrudService<GpContractEntity, GpContractDTO> {

    /**
     * 获取股票列表（简化信息）
     * @param params 查询参数
     * @return 股票列表
     */
    PageData<GpContractListDTO> pageList(Map<String, Object> params);

    /**
     * 获取所有股票列表（简化信息）
     * @param params 查询参数
     * @return 股票列表
     */
    List<GpContractListDTO> listAll(Map<String, Object> params);
}
