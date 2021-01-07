package com.cuiwz.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.HallsRepsVO;
import com.cuiwz.controller.vo.HallsReqVO;
import com.cuiwz.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author cuiwz
 * @since 2021-01-06
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {

    /**
     * 查询影厅
     * 形参列表第一个必须是Page对象
     * @return
     */
    IPage<HallsRepsVO> describeHalls(Page<HallsReqVO> page, @Param("ew") QueryWrapper queryWrapper);

}
