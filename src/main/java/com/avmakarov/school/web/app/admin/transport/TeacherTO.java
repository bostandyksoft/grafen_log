package com.avmakarov.school.web.app.admin.transport;

import jakarta.validation.constraints.NotNull;

public record TeacherTO(Long oid, @NotNull String fullName) {
}
