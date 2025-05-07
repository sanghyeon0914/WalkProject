package org.example.walkproject.reply.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.walkproject.reply.entity.Reply;

@Getter
@Builder
public class ReplyResponseDto {
    private Long id;
    private String content;
    private String userId;

    public static ReplyResponseDto fromEntity(Reply reply){
        return ReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .userId(reply.getUserId())
                .build();
    }
}
