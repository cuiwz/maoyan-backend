package com.cuiwz.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.DescribeFilmRespVO;
import com.cuiwz.controller.vo.DescribeFilmsRespVO;
import com.cuiwz.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author cuiwz
 * @since 2021-01-06
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmsRespVO> page);

    DescribeFilmRespVO describeFilmById(@Param("filmId") String filmId);

}
