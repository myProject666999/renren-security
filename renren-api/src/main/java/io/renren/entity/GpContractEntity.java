package io.renren.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("gp_contract")
public class GpContractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private Integer contractId;
    private String symbol;
    private String secType;
    private String currency;
    private String exchange;
    private BigDecimal marketPrice;
    private Long creator;
    private Date createDate;
}
