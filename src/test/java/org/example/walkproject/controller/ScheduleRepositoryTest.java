package org.example.walkproject.controller;

import org.example.walkproject.schedule.entity.Schedule;
import org.example.walkproject.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleRepositoryTest {

    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("schedule 생성 확인 testCode")
    public void createSchedule(){
        //Given
        Schedule schedule = Schedule.builder().id(1L).title("제목").content("내용").build();

        //When & Then
        Assertions.assertEquals(1L, (long) schedule.getId());
        Assertions.assertEquals("제목", schedule.getTitle());
        Assertions.assertEquals("내용", schedule.getContent());

    }

    @Test
    @DisplayName("schedule 수정 확인 testCode")
    public void updateSchedule(){
        /*//Given
        Schedule schedule = Schedule.builder().id(1L).userId(1).title("제목").content("내용").build();

        //When
        schedule.getUserId(2);

        //Then
        Assertions.assertEquals(1L, (long) schedule.getId());
        Assertions.assertEquals(2, schedule.getUserId());
        Assertions.assertEquals("제목", schedule.getTitle());
        Assertions.assertEquals("내용", schedule.getContent());*/

    }


}
