package com.snow.learn.controller.sales;


import com.snow.learn.entity.sales.Sales;
import com.snow.learn.service.IElasticService;
import com.snow.learn.service.sales.SalesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @since 2021-05-08
 */
@RestController
@RequestMapping("/sales")
public class SalesController {
    @Resource
    private SalesService service;

    @Resource
    private IElasticService elasticService;


    @ApiOperation("查询销售列表")
    @RequestMapping("getSalesList")
    public List<Sales> getSalesList(){
        return service.getSalesList();
    }



}

