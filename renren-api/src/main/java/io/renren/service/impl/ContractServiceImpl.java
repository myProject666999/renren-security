/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service.impl;

import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.dao.ContractDao;
import io.renren.entity.ContractEntity;
import io.renren.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 股票合约服务实现类
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class ContractServiceImpl extends BaseServiceImpl<ContractDao, ContractEntity> implements ContractService {

    @Override
    public List<ContractEntity> getSimpleList(Map<String, Object> params) {
        return baseDao.getSimpleList(params);
    }

    @Override
    public ContractEntity getDetail(Long id) {
        return selectById(id);
    }

}
