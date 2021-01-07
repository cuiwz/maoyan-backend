package com.cuiwz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuiwz.controller.vo.DescribeActorsRespVO;
import com.cuiwz.controller.vo.DescribeFilmRespVO;
import com.cuiwz.controller.vo.DescribeFilmsRespVO;
import com.cuiwz.controller.vo.FilmSavedReqVO;
import com.cuiwz.exception.CommonServiceException;
import com.cuiwz.service.FilmService;
import com.cuiwz.vo.BasePageVO;
import com.cuiwz.vo.BaseResponseVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author cuiwz
 * @Date 2020/5/16 10:28
 */
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    /**
     * 获取演员列表
     * @param basePageVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public BaseResponseVO describeActors(BasePageVO basePageVO) throws CommonServiceException {

        // 检查入参
        basePageVO.checkParam();

        // 调用逻辑层，获取返回参数
        IPage<DescribeActorsRespVO> page =
                filmService.describeActors(basePageVO.getNowPage(), basePageVO.getPageSize());

        Map<String, Object> result = describePageResult(page, "actors");

        return BaseResponseVO.success(result);
    }

    /**
     * 获取电影列表
     * @param basePageVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponseVO describeFilms(BasePageVO basePageVO) throws CommonServiceException {

        // 检查入参
        basePageVO.checkParam();

        // 调用逻辑层，获取返回参数
        IPage<DescribeFilmsRespVO> page =
                filmService.describeFilms(basePageVO.getNowPage(), basePageVO.getPageSize());

        Map<String, Object> result = describePageResult(page, "films");

        return BaseResponseVO.success(result);
    }

    /**
     * 根据电影主键获取电影信息
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/{filmId}", method = RequestMethod.GET)
    public BaseResponseVO describeFilmById(@PathVariable("filmId") String filmId) throws CommonServiceException {

        DescribeFilmRespVO describeFilmRespVO = filmService.describeFilmById(filmId);
        if (describeFilmRespVO == null) {
            return BaseResponseVO.success();
        } else {
            return BaseResponseVO.success(describeFilmRespVO);
        }

    }

    /**
     * 添加电影
     * @param filmSavedReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/film:add", method = RequestMethod.POST)
    public BaseResponseVO addFilmInfo(@RequestBody FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

        filmService.saveFilm(filmSavedReqVO);

        return BaseResponseVO.success();

    }

    /**
     * 获取分页对象的公共接口
     * @param page
     * @param title
     * @return
     */
    private Map<String, Object> describePageResult(IPage page, String title) {
        Map<String, Object> result = new HashMap<>();

        result.put("totalSize", page.getTotal());
        result.put("totalPages", page.getPages());
        result.put("pageSize", page.getSize());
        result.put("nowPage", page.getCurrent());

        result.put(title, page.getRecords());

        return result;
    }

}
