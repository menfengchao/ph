package org.men.decorator;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName UserRedPacket
 * @Description
 * @Author
 * @Date 2020/11/3 10:17
 * @Version V1.0.0
 **/
@Data
public class UserRedPacket {

    private int id; //红包ID
    private int userId; //领取用户ID
    private String sku; //商品SKU
    private BigDecimal redPacket; //领取红包金额

}
