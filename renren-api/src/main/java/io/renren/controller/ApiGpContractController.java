package io.renren.controller;

import io.renren.common.utils.Result;
import io.renren.dto.GpContractListDTO;
import io.renren.entity.GpContractEntity;
import io.renren.service.GpContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
@Api(tags = "股票接口")
public class ApiGpContractController {

    @Autowired
    private GpContractService gpContractService;

    @GetMapping("/list")
    @ApiOperation(value = "获取股票列表", response = GpContractListDTO.class, responseContainer = "List")
    public Result<List<GpContractListDTO>> list() {
        List<GpContractListDTO> list = gpContractService.getList();
        return new Result<List<GpContractListDTO>>().ok(list);
    }

    @GetMapping("/detail/{contractId}")
    @ApiOperation(value = "获取股票详情", response = GpContractEntity.class)
    public Result<GpContractEntity> detail(@ApiParam(value = "股票ID", required = true) @PathVariable("contractId") Integer contractId) {
        GpContractEntity entity = gpContractService.getByContractId(contractId);
        return new Result<GpContractEntity>().ok(entity);
    }
}
