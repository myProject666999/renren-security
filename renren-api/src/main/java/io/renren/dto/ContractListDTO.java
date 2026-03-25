/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 股票列表DTO
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "股票列表信息")
public class ContractListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "股票ID")
    private String stockId;

    @ApiModelProperty(value = "股票代码")
    private String stockCode;

    @ApiModelProperty(value = "交易所")
    private String exchange;

    @ApiModelProperty(value = "股票价格")
    private BigDecimal price;

}
