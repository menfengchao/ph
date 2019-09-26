package org.men.mmodulearticle.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @ClassName Article
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/26 10:14
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "a_article")
public class Article {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "title",length = 255)
    private String title;

    @Lob
    private String content;

    @Column(name = "type",length = 16)
    private int type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;

    @Column(name = "user_id",length = 32)
    private String userId;

    @Column(name = "status",length = 16)
    private int status;

}
