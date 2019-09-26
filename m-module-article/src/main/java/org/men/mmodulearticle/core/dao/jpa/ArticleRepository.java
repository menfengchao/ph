package org.men.mmodulearticle.core.dao.jpa;

import org.men.mmodulearticle.core.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ArticleRepository
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 15:47
 * @Version 1.0
 **/
public interface ArticleRepository extends JpaRepository<Article, String> {
}
