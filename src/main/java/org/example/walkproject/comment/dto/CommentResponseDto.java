package org.example.walkproject.comment.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.walkproject.comment.entity.Comment;

@Getter
@Builder
public class CommentResponseDto {
    private long id;
    private String content;
    private String userId;

    public static CommentResponseDto fromEntity(Comment comment){
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .build();
    }

}
