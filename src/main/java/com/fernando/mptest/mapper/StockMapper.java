package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Repository
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    public List<Stock> findAllStock();

    @Select("select * from stock where stock_name like CONCAT('%',#{stock_name},'%')")
    public List<Stock> findByName(@Param("stock_name")String stock_name);

    @Select("select * from stock where code like CONCAT('%',#{code},'%')")
    List<Stock> findByCode(@Param("code")String code);


    @Select("select stock.*, sum(amount * T.price) as keyValue\n" +
            "from (select * from deal where method = '买入'\n" +
            "      order by trade_time desc limit 800) as T, stock\n" +
            "where T.code = stock.code group by code\n" +
            "order by keyValue desc limit 10;")
    public List<Map<String, Object>> getHotStock();

    @Select("select * from stock order by price * fluctuation desc")
    public List<Stock> getMostRiseStock();


}
