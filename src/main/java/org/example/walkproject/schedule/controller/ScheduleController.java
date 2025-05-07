package org.example.walkproject.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.walkproject.schedule.dto.ScheduleRequsetDto;
import org.example.walkproject.schedule.dto.ScheduleResponseDto;
import org.example.walkproject.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    //할일 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequsetDto dto){
        ScheduleResponseDto response = scheduleService.createSchedule(dto);
        return ResponseEntity.ok(response);
    }

    //전체조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    //할일 + 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> schedulesByIdWithComment(@PathVariable("id") Long id){
        ScheduleResponseDto schedules = scheduleService.findByIdWithComments(id);
        return ResponseEntity.ok(schedules);
    }

    //할일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable("id") Long id,
            @RequestBody ScheduleRequsetDto dto){
        ScheduleResponseDto schedules = scheduleService.updateSchedule(id, dto);
        return ResponseEntity.ok(schedules);
    }

    //할일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Long id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
