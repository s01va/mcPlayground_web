package com.mcplayground.webboard.web.dto;

import com.mcplayground.webboard.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long seq;
    private String title;
    private String content;

    public PostsResponseDto(Posts entity) {
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
