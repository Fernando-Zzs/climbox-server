package com.fernando.mptest.mapper;

import com.fernando.mptest.model.Star;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fernando.mptest.model.Stock;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fernando
 * @since 2021-10-27
 */
@Repository
@Mapper
public interface StarMapper extends BaseMapper<Star> {

    @Select("select distinct stock.* from star, stock, user where star.user_id = #{id} and star.stock_code = stock.code;")
    public List<Stock> findStarByUserId(@Param("id") String id);

    @Delete("delete from star where user_id = #{user_id} and stock_code = #{stock_code};")
    public int deleteById(@Param("user_id") String user_id, @Param("stock_code") String stock_code);

    @Insert("insert into star values(#{user_id},#{stock_code})")
    public int followById(@Param("user_id") String user_id,@Param("stock_code") String stock_code);

}
