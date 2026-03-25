/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service;

import io.renren.common.service.BaseService;
import io.renren.entity.ContractEntity;

import java.util.List;
import java.util.Map;

/**
 * 股票合约服务接口
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface ContractService extends BaseService<ContractEntity> {

    /**
     * 查询股票列表信息（只返回id、股票id、股票代码、交易所和股票价格）
     */
    List<ContractEntity> getSimpleList(Map<String, Object> params);

    /**
     * 查询股票详情信息
     */
    ContractEntity getDetail(Long id);

}
