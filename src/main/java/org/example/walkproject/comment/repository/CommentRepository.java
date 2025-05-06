package org.example.walkproject.comment.repository;

import org.example.walkproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(Long scheduleId);

    @Query("SELECT c.schedule.id, COUNT(c.id) FROM Comment c GROUP BY c.schedule.id")
    List<Object[]> countCommentsByScheduleId();

}
