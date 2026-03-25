package io.renren.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "股票列表信息")
public class GpContractListDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;
    
    @ApiModelProperty(value = "股票id")
    private Integer contractId;
    
    @ApiModelProperty(value = "股票代码")
    private String symbol;
    
    @ApiModelProperty(value = "交易所")
    private String exchange;
    
    @ApiModelProperty(value = "股票价格")
    private BigDecimal marketPrice;
}
