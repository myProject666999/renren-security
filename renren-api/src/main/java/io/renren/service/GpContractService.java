package io.renren.service;

import io.renren.common.service.BaseService;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;

import java.util.List;

public interface GpContractService extends BaseService<GpContractEntity> {
    
    List<GpContractListDTO> getList();
    
    GpContractEntity getByContractId(Integer contractId);
}
