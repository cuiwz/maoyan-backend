package com.cuiwz.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.DescribeCinemaRespVO;
import com.cuiwz.dao.entity.MoocCinemaT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 影院信息表 Mapper 接口
 * </p>
 *
 * @author cuiwz
 * @since 2021-01-06
 */
public interface MoocCinemaTMapper extends BaseMapper<MoocCinemaT> {

    IPage<DescribeCinemaRespVO> describeCinemas(Page<DescribeCinemaRespVO> page);

}
