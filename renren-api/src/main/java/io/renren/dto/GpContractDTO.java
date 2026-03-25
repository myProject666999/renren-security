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
import java.util.Date;

/**
 * 股票合约DTO - 包含所有字段
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "股票合约详情")
public class GpContractDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "股票ID")
    private Integer contractId;

    @ApiModelProperty(value = "股票代码")
    private String symbol;

    @ApiModelProperty(value = "证券类型")
    private String secType;

    @ApiModelProperty(value = "货币类型")
    private String currency;

    @ApiModelProperty(value = "交易所")
    private String exchange;

    @ApiModelProperty(value = "股票价格")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "创建者")
    private Long creator;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;
}
