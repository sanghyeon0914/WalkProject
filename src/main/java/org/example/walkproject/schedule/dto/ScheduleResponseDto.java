package org.example.walkproject.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.walkproject.comment.dto.CommentResponseDto;
import org.example.walkproject.schedule.entity.Schedule;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
public class ScheduleResponseDto {
    private long id;
    private String title;
    private String content;
    private String userId;
    private List<CommentResponseDto> comments;
    private long commentCnt;

    public static ScheduleResponseDto fromEntity(Schedule schedule, List<CommentResponseDto> comments) {
        long totalCommentCount = 0;

        if (comments != null) {
            for (CommentResponseDto comment : comments) {
                totalCommentCount++; // 댓글 1개
                if (comment.getReplies() != null) {
                    totalCommentCount += comment.getReplies().size(); // 대댓글 개수 추가
                }
            }
        }

        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .userId(schedule.getUserId())
                .comments(comments != null ? comments : new ArrayList<>())
                .commentCnt(totalCommentCount)
                .build();
    }

    // 오버로딩
    // 두 메서드 모두 이름은 fromEntity지만
    // 매개변수 개수가 다르기 때문에 문제 없음
    public static ScheduleResponseDto fromEntity(Schedule schedule) {
        return fromEntity(schedule, new ArrayList<>());
    }

    public static ScheduleResponseDto from(Schedule schedule, Long commentCnt) {
        return ScheduleResponseDto.builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .userId(schedule.getUserId())
                .commentCnt(commentCnt)
                .build();
    }
}
