package com.cuiwz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.DescribeActorsRespVO;
import com.cuiwz.controller.vo.DescribeFilmRespVO;
import com.cuiwz.controller.vo.DescribeFilmsRespVO;
import com.cuiwz.controller.vo.FilmSavedReqVO;
import com.cuiwz.exception.CommonServiceException;

/**
 * @Author cuiwz
 * @Date 2020/5/16 11:10
 */
public interface FilmService {

    /**
     * 获取演员列表
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException;

    /**
     * 获取电影列表
     * @param nowPage
     * @param pageSize
     * @return
     * @throws CommonServiceException
     */
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    /**
     * 根据主键获取电影信息
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;

    /**
     * 保存电影信息
     * @param filmSavedReqVO
     * @throws CommonServiceException
     */
    void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException;

}
