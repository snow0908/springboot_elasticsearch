package com.snow.learn.service.sales;

import com.snow.learn.entity.sales.Sales;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lx
 * @since 2021-05-08
 */
public interface SalesService extends IService<Sales> {
    List<Sales> getSalesList();

}
