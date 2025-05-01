package org.example.walkproject.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter // 중복을 줄이고, 개발 효율성을 높이기 위함
@AllArgsConstructor //코드의 가독성을 높이며, 의존성 주입
public class Post {
    private long id;
    private String title;
    private String content;

    @Builder
    public Post(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;

    }
}
