package io.renren.service.impl;

import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.dao.GpContractDao;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;
import io.renren.service.GpContractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpContractServiceImpl extends BaseServiceImpl<GpContractDao, GpContractEntity> implements GpContractService {

    @Override
    public List<GpContractListDTO> getList() {
        return baseDao.getList();
    }

    @Override
    public GpContractEntity getByContractId(Integer contractId) {
        return baseDao.getByContractId(contractId);
    }
}
