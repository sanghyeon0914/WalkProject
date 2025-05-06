package org.example.walkproject.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.comment.dto.CommentRequestDto;
import org.example.walkproject.comment.dto.CommentResponseDto;
import org.example.walkproject.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> CreateComment(
            @PathVariable("scheduleId") Long scheduleId,
            @RequestBody CommentRequestDto dto){
        CommentResponseDto response = commentService.createComment(scheduleId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(
            @PathVariable("scheduleId") Long scheduleId){
        List<CommentResponseDto> comments = commentService.findAllComments(scheduleId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> CommentById(
            @PathVariable("id") Long id){
        CommentResponseDto response = commentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable("id") Long id,
            @RequestBody CommentRequestDto dto){
        CommentResponseDto response = commentService.updateComment(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}











