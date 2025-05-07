package org.example.walkproject.comment.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.walkproject.comment.entity.Comment;
import org.example.walkproject.reply.dto.ReplyResponseDto;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CommentResponseDto {
    private long id;
    private String content;
    private String userId;
    private List<ReplyResponseDto> replies;

    public static CommentResponseDto fromEntity(Comment comment, List<ReplyResponseDto> replies) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .replies(replies)
                .build();
    }

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .replies(new ArrayList<>()) // ← replies 없는 경우엔 빈 리스트로 초기화
                .build();
    }

}
