/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.Result;
import io.renren.dto.ContractDetailDTO;
import io.renren.dto.ContractListDTO;
import io.renren.entity.ContractEntity;
import io.renren.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 股票合约接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api/contract")
@Api(tags = "股票合约接口")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("list")
    @ApiOperation(value = "获取股票列表信息", response = ContractListDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stockCode", value = "股票代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "exchange", value = "交易所", paramType = "query", dataType = "String")
    })
    public Result<List<ContractListDTO>> list(String stockCode, String exchange) {
        Map<String, Object> params = new HashMap<>();
        params.put("stockCode", stockCode);
        params.put("exchange", exchange);
        List<ContractEntity> list = contractService.getSimpleList(params);
        List<ContractListDTO> dtoList = ConvertUtils.sourceToTarget(list, ContractListDTO.class);
        return new Result<List<ContractListDTO>>().ok(dtoList);
    }

    @GetMapping("detail/{id}")
    @ApiOperation(value = "获取股票详情信息", response = ContractDetailDTO.class)
    @ApiImplicitParam(name = "id", value = "主键ID", paramType = "path", required = true, dataType = "Long")
    public Result<ContractDetailDTO> detail(@PathVariable("id") Long id) {
        ContractEntity contract = contractService.getDetail(id);
        ContractDetailDTO dto = ConvertUtils.sourceToTarget(contract, ContractDetailDTO.class);
        return new Result<ContractDetailDTO>().ok(dto);
    }

}
