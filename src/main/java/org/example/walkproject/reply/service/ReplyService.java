package org.example.walkproject.reply.service;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.comment.entity.Comment;
import org.example.walkproject.comment.repository.CommentRepository;
import org.example.walkproject.comment.service.CommentService;
import org.example.walkproject.reply.dto.ReplyRequestDto;
import org.example.walkproject.reply.dto.ReplyResponseDto;
import org.example.walkproject.reply.entity.Reply;
import org.example.walkproject.reply.repository.ReplyRepository;
import org.hibernate.usertype.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public ReplyResponseDto createReply(Long commentId, ReplyRequestDto dto){

        if (replyRepository.existsById(commentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글이 아닌 대댓글에는 대댓글을 작성할 수 없습니다.");
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글을 찾을 수 없습니다."));

        Reply reply = Reply.builder()
                .comment(comment)
                .content(dto.getContent())
                .userId(dto.getUserId())
                .build();

        replyRepository.save(reply);

        return ReplyResponseDto.fromEntity(reply);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAllReplys(Long commentId) {
        return replyRepository.findByCommentId(commentId).stream()
                .map(ReplyResponseDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ReplyResponseDto findById(long id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        return ReplyResponseDto.fromEntity(reply);
    }

    @Transactional
    public ReplyResponseDto updateReply(long id, ReplyRequestDto dto){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"댓글을 찾을 수 없습니다."));

        reply.update(dto.getContent(),dto.getUserId());

        return ReplyResponseDto.fromEntity(reply);
    }

    @Transactional
    public void deleteReply(long id){
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));

        replyRepository.delete(reply);
    }

}
