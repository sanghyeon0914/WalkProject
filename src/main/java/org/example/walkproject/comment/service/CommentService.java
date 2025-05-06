package org.example.walkproject.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.comment.dto.CommentRequestDto;
import org.example.walkproject.comment.dto.CommentResponseDto;
import org.example.walkproject.comment.entity.Comment;
import org.example.walkproject.comment.repository.CommentRepository;
import org.example.walkproject.schedule.entity.Schedule;
import org.example.walkproject.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(Long scheduleId,CommentRequestDto dto){

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .schedule(schedule)
                .content(dto.getContent())
                .userId(dto.getUserId())
                .build();

        Comment saved = commentRepository.save(comment);

        return CommentResponseDto.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllComments(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        return CommentResponseDto.fromEntity(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(long id, CommentRequestDto dto){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글을 찾을 수 없습니다."));

        comment.update(dto.getContent(),dto.getUserId());

        return CommentResponseDto.fromEntity(comment);
    }

    @Transactional
    public void deleteComment(long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }

}










