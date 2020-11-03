package org.men.decorator;

import java.math.BigDecimal;

/**
 * @ClassName IBaseCount
 * @Description 计算支付金额接口类
 * @Author
 * @Date 2020/11/3 10:17
 * @Version V1.0.0
 **/
public interface IBaseCount {

    BigDecimal countPayMoney(OrderDetail orderDetail);

}
