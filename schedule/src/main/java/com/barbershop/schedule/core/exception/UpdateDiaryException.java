package com.barbershop.schedule.core.exception;

import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.UPDATE_DIARY_ERROR_MSG;
import static com.barbershop.schedule.core.constants.ScheduleErrorMessages.UPDATE_DIARY_ERROR_TITLE;

public class UpdateDiaryException extends ScheduleException {
    public UpdateDiaryException() {
        super(UPDATE_DIARY_ERROR_TITLE, UPDATE_DIARY_ERROR_MSG);
    }
}
