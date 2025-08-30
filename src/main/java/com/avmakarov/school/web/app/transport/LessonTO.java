package com.avmakarov.school.web.app.transport;

import com.avmakarov.school.model.domain.LessonStudent;

import java.time.LocalDateTime;
import java.util.List;

public record LessonTO(
        Long oid,
        LocalDateTime date,
        Long schoolClass,
        Long subject,
        String topic,
        List<Object> skills,
        List<LessonStudentTO> students
) {
    public record LessonStudentTO(
            Long studentId,
            LessonStudent.State state,
            int grade,
            String comment
    ) {
    }


}
