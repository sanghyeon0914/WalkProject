package org.example.walkproject.reply.contorller;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.reply.dto.ReplyRequestDto;
import org.example.walkproject.reply.dto.ReplyResponseDto;
import org.example.walkproject.reply.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments/{commentId}/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponseDto> createReply(
            @PathVariable("commentId") Long commentId,
            @RequestBody ReplyRequestDto dto){
        ReplyResponseDto response = replyService.createReply(commentId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ReplyResponseDto>> getAllReplys(
            @PathVariable("commentId") Long commentId){
        List<ReplyResponseDto> reply = replyService.findAllReplys(commentId);
        return ResponseEntity.ok(reply);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> ReplyById(
            @PathVariable("id") Long id){
        ReplyResponseDto response = replyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReplyResponseDto> updateReply(
            @PathVariable("id") Long id,
            @RequestBody ReplyRequestDto dto){
        ReplyResponseDto response = replyService.updateReply(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReply(
            @PathVariable("id") Long id){
        replyService.deleteReply(id);
        return ResponseEntity.ok().build();
    }

}
