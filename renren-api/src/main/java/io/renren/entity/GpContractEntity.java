/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票合约表
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("gp_contract")
public class GpContractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * 股票id (主键)
     */
    @TableId("contract_id")
    private Integer contractId;

    /**
     * 股票代码
     */
    @TableField("symbol")
    private String symbol;

    /**
     * 证券类型
     */
    @TableField("sec_type")
    private String secType;

    /**
     * 货币类型
     */
    @TableField("currency")
    private String currency;

    /**
     * 交易所
     */
    @TableField("exchange")
    private String exchange;

    /**
     * 股票价格
     */
    @TableField("market_price")
    private BigDecimal marketPrice;

    /**
     * 创建者
     */
    @TableField("creator")
    private Long creator;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
}
