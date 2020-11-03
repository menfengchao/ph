package org.men.decorator;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName OrderDetail
 * @Description
 * @Author
 * @Date 2020/11/3 10:08
 * @Version V1.0.0
 **/
@Data
public class OrderDetail {
    private int id; //详细订单ID
    private int orderId;//主订单ID
    private Merchandise merchandise; //商品详情
    private BigDecimal payMoney; //支付单价
}
