package com.cuiwz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuiwz.controller.vo.DescribeActorsRespVO;
import com.cuiwz.controller.vo.DescribeFilmRespVO;
import com.cuiwz.controller.vo.DescribeFilmsRespVO;
import com.cuiwz.controller.vo.FilmSavedReqVO;
import com.cuiwz.dao.entity.MoocFilmActorT;
import com.cuiwz.dao.entity.MoocFilmInfoT;
import com.cuiwz.dao.entity.MoocFilmT;
import com.cuiwz.dao.mapper.MoocActorTMapper;
import com.cuiwz.dao.mapper.MoocFilmActorTMapper;
import com.cuiwz.dao.mapper.MoocFilmInfoTMapper;
import com.cuiwz.dao.mapper.MoocFilmTMapper;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.FilmService;
import com.cuiwz.utils.ToolUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author cuiwz
 * @Date 2020/5/16 11:10
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Resource
    private MoocActorTMapper actorTMapper;

    @Resource
    private MoocFilmTMapper filmTMapper;

    @Resource
    private MoocFilmInfoTMapper filmInfoTMapper;

    @Resource
    private MoocFilmActorTMapper filmActorTMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize)
            throws CommonServiceException {
        // 查询演员列表
        return actorTMapper.describeActors(new Page<>(nowPage, pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize)
            throws CommonServiceException {
        // 查询电影列表
        return filmTMapper.describeFilms(new Page<>(nowPage, pageSize));
    }

    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        // 根据主键获取影片信息
        return filmTMapper.describeFilmById(filmId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilm(FilmSavedReqVO reqVO) throws CommonServiceException {
        // 保存电影信息
        try {
            // 保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));

            filmTMapper.insert(film);

            // 保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();
            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());

            filmInfoTMapper.insert(filmInfo);

            // 保存演员映射表
            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if (actorId.length != roleNames.length) {
                throw new CommonServiceException(500, "演员和角色名数量不匹配");
            }

            for(int i=0; i < actorId.length; i++){
                // 保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();

                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);


                filmActorTMapper.insert(filmActor);
            }
        } catch (Exception e) {
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}
