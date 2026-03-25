/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;

import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.dto.GpContractDTO;
import io.renren.dto.GpContractListDTO;
import io.renren.service.GpContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 股票合约接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api/gpContract")
@Api(tags = "股票合约接口")
public class GpContractController {

    @Autowired
    private GpContractService gpContractService;

    /**
     * 股票列表（简化信息）
     * 包含：id、股票id(contractId)、股票代码(symbol)、交易所、股票价格(marketPrice)
     */
    @GetMapping("/list")
    @ApiOperation(value = "股票列表", notes = "获取股票列表信息，包含id、股票id、股票代码、交易所、股票价格")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "symbol", value = "股票代码", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "exchange", value = "交易所", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "secType", value = "证券类型", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "currency", value = "货币类型", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    public Result<PageData<GpContractListDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<GpContractListDTO> page = gpContractService.pageList(params);
        return new Result<PageData<GpContractListDTO>>().ok(page);
    }

    /**
     * 获取所有股票列表（不分页）
     */
    @GetMapping("/all")
    @ApiOperation(value = "所有股票列表", notes = "获取所有股票列表信息（不分页），包含id、股票id、股票代码、交易所、股票价格")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "symbol", value = "股票代码", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "exchange", value = "交易所", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "secType", value = "证券类型", paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "currency", value = "货币类型", paramType = "query", dataType = "String")
    })
    public Result<List<GpContractListDTO>> all(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<GpContractListDTO> list = gpContractService.listAll(params);
        return new Result<List<GpContractListDTO>>().ok(list);
    }

    /**
     * 股票详情
     * 包含表中所有字段
     */
    @GetMapping("/{contractId}")
    @ApiOperation(value = "股票详情", notes = "获取股票详情信息，包含表中所有字段")
    @ApiImplicitParam(name = "contractId", value = "股票ID(contract_id)", paramType = "path", required = true, dataType = "Integer")
    public Result<GpContractDTO> get(@PathVariable("contractId") Integer contractId) {
        GpContractDTO data = gpContractService.get(contractId.longValue());
        if (data == null) {
            return new Result<GpContractDTO>().error(404, "股票不存在");
        }
        return new Result<GpContractDTO>().ok(data);
    }
}
