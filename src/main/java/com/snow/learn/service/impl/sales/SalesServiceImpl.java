package com.snow.learn.service.impl.sales;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snow.learn.entity.sales.Sales;
import com.snow.learn.mapper.sales.SalesMapper;
import com.snow.learn.service.sales.SalesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lx
 * @since 2021-05-08
 */
@Service
public class SalesServiceImpl extends ServiceImpl<SalesMapper, Sales> implements SalesService {
    @Override
    public List<Sales> getSalesList() {
        return list();
    }
}
