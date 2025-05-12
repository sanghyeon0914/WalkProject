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

    @Query(value =
            "SELECT c.schedule_id, COUNT(c.id)" +
                    "(SELECT COUNT(r.id) FROM replies r "+
                    "JOIN comments c2 ON r.comment_id = c2.id " +
                    "WHERE c2.schedule_id = c.schedule_id) " +
                    "FROM comments c GROUP BY c.schedule_id",
            nativeQuery = true)
    List<Object[]> countAllCommentsAndRepliesByScheduleId();

}
