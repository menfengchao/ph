package org.men.decorator;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName Order
 * @Description
 * @Author
 * @Date 2020/11/3 10:07
 * @Version V1.0.0
 **/
@Data
public class Order {
    private int id; //订单ID
    private String orderNo; //订单号
    private BigDecimal totalPayMoney; //总支付金额
    private List<OrderDetail> list; //详细订单列表
}
