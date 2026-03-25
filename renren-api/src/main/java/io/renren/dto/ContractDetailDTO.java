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
 * 股票详情DTO
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "股票详情信息")
public class ContractDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "股票ID")
    private String stockId;

    @ApiModelProperty(value = "股票代码")
    private String stockCode;

    @ApiModelProperty(value = "股票名称")
    private String stockName;

    @ApiModelProperty(value = "交易所")
    private String exchange;

    @ApiModelProperty(value = "股票价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场类型")
    private String market;

    @ApiModelProperty(value = "货币类型")
    private String currency;

    @ApiModelProperty(value = "状态 0:禁用 1:启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
