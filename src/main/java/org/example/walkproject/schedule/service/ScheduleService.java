package org.example.walkproject.schedule.service;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.comment.dto.CommentResponseDto;
import org.example.walkproject.comment.repository.CommentRepository;
import org.example.walkproject.reply.dto.ReplyResponseDto;
import org.example.walkproject.reply.entity.Reply;
import org.example.walkproject.reply.repository.ReplyRepository;
import org.example.walkproject.schedule.dto.ScheduleRequestDto;
import org.example.walkproject.schedule.dto.ScheduleResponseDto;
import org.example.walkproject.schedule.entity.Schedule;
import org.example.walkproject.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.example.walkproject.comment.entity.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor //
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        Schedule schedule = Schedule.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userId(dto.getUserId())
                .build();

        Schedule saved  = scheduleRepository.save(schedule);

        return ScheduleResponseDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        // 댓글 개수 맵으로 변환
        Map<Long, Long> commentCountMap = new HashMap<>();
        for (Object[] row : commentRepository.countAllCommentsAndRepliesByScheduleId()) {
            Long scheduleId = (Long) row[0];
            Long count = (Long) row[1];
            commentCountMap.put(scheduleId, count);
        }

        // Dto로 변환
        return schedules.stream()
                .map(schedule -> {
                    Long count = commentCountMap.getOrDefault(schedule.getId(), 0L);
                    return ScheduleResponseDto.from(schedule, count);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findByIdWithComments(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스케줄을 찾을 수 없습니다."));

        List<Comment> comments = commentRepository.findByScheduleId(id);

        List<CommentResponseDto> commentDtos = comments.stream()
                .map(comment -> {
                    // 해당 댓글에 달린 대댓글 가져오기
                    List<Reply> replies = replyRepository.findByCommentId(comment.getId());

                    List<ReplyResponseDto> replyDtos = replies.stream()
                            .map(ReplyResponseDto::fromEntity)
                            .toList();

                    return CommentResponseDto.fromEntity(comment, replyDtos);
                })
                .toList();

        return ScheduleResponseDto.fromEntity(schedule, commentDtos);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,"스케줄을 찾을 수 없습니다."));

        schedule.update(dto.getTitle(),dto.getContent(),dto.getUserId());

        return ScheduleResponseDto.fromEntity(schedule);
    }

    @Transactional
    public void deleteSchedule(long id) {
        Schedule schedule = scheduleRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 할 Schedule이 없습니다."));

        scheduleRepository.delete(schedule);
    }

}
