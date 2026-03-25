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
 * 股票合约
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("gp_contract")
public class ContractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private Long id;

	/**
	 * 股票ID
	 */
	@TableField("stock_id")
	private String stockId;

	/**
	 * 股票代码
	 */
	@TableField("stock_code")
	private String stockCode;

	/**
	 * 股票名称
	 */
	@TableField("stock_name")
	private String stockName;

	/**
	 * 交易所
	 */
	@TableField("exchange")
	private String exchange;

	/**
	 * 股票价格
	 */
	@TableField("price")
	private BigDecimal price;

	/**
	 * 市场类型
	 */
	@TableField("market")
	private String market;

	/**
	 * 货币类型
	 */
	@TableField("currency")
	private String currency;

	/**
	 * 状态 0:禁用 1:启用
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

}
