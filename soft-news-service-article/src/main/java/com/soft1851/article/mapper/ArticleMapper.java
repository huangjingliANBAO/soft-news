package com.soft1851.article.mapper;

import com.soft1851.my.mapper.MyMapper;
import com.soft1851.pojo.Article;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author crq
 */
@Repository
public interface ArticleMapper extends Mapper<Article> {
}