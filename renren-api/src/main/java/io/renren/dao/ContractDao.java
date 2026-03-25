/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.dao;

import io.renren.common.dao.BaseDao;
import io.renren.entity.ContractEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 股票合约
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface ContractDao extends BaseDao<ContractEntity> {

    /**
     * 查询股票列表信息（只返回部分字段）
     */
    List<ContractEntity> getSimpleList(Map<String, Object> params);

}
