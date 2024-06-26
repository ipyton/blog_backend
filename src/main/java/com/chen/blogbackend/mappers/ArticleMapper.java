package com.chen.blogbackend.mappers;

import com.chen.blogbackend.DAO.ArticleDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    @DaoFactory
    ArticleDao getArticleDao();
}
