package io.renren.dao;

import io.renren.common.dao.BaseDao;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GpContractDao extends BaseDao<GpContractEntity> {
    
    List<GpContractListDTO> getList();
    
    GpContractEntity getByContractId(@Param("contractId") Integer contractId);
}
