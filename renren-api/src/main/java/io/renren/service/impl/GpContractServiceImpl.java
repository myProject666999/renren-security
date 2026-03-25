/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.page.PageData;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.dao.GpContractDao;
import io.renren.dto.GpContractDTO;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;
import io.renren.service.GpContractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 股票合约服务实现类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class GpContractServiceImpl extends CrudServiceImpl<GpContractDao, GpContractEntity, GpContractDTO> implements GpContractService {

    @Override
    public QueryWrapper<GpContractEntity> getWrapper(Map<String, Object> params) {
        String symbol = (String) params.get("symbol");
        String exchange = (String) params.get("exchange");
        String secType = (String) params.get("secType");
        String currency = (String) params.get("currency");

        QueryWrapper<GpContractEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(symbol), "symbol", symbol);
        wrapper.eq(StringUtils.isNotBlank(exchange), "exchange", exchange);
        wrapper.eq(StringUtils.isNotBlank(secType), "sec_type", secType);
        wrapper.eq(StringUtils.isNotBlank(currency), "currency", currency);

        return wrapper;
    }

    @Override
    public PageData<GpContractListDTO> pageList(Map<String, Object> params) {
        IPage<GpContractEntity> page = baseDao.selectPage(
            getPage(params, null, false),
            getWrapper(params)
        );

        return getPageData(page, GpContractListDTO.class);
    }

    @Override
    public List<GpContractListDTO> listAll(Map<String, Object> params) {
        List<GpContractEntity> entityList = baseDao.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList, GpContractListDTO.class);
    }

    @Override
    public GpContractDTO get(Long id) {
        GpContractEntity entity = baseDao.selectById(id);
        if (entity == null) {
            return null;
        }
        return ConvertUtils.sourceToTarget(entity, GpContractDTO.class);
    }
}
