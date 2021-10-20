package com.fernando.mptest.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Follow extends Model {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String expertId;


}
