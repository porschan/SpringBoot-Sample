package com.debug.mooc.dubbo.one.api.request;/**
 * Created by Administrator on 2019/1/14.
 */

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:debug (SteadyJack)
 * @Date: 2019/1/14 22:18
 **/
@Data
@ToString
public class PushOrderDto implements Serializable{

    //商品id
    private Integer itemId;

    //下单数量
    private Integer total;

    //客户姓名
    private String customerName;
}


























