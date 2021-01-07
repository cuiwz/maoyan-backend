package com.cuiwz.controller.vo;

import lombok.Data;

/**
 * @Author cuiwz
 * @Date 2020/5/16 11:38
 */
@Data
public class DescribeFilmRespVO {

    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;

}
