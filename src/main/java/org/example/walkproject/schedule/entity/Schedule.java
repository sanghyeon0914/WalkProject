package org.example.walkproject.schedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.walkproject.comment.entity.Comment;
import org.example.walkproject.common.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter //클래스 내 모든 필드의 Getter 메소드 자동 생성 - lombok
@Entity
@AllArgsConstructor //코드의 가독성을 높이며, 의존성 주입 - lombok
@NoArgsConstructor //기본 생성자 자동 추가 - lombok
@Builder
@Table(name = "schedules")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 기본 키를 자동으로 생성하도록 위임한다는 뜻
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String userId;

    public void update(String title, String content, String userId){
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

}
