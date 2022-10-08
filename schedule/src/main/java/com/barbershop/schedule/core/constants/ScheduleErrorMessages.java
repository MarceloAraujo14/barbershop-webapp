package com.barbershop.schedule.core.constants;

public class ScheduleErrorMessages {

    private ScheduleErrorMessages(){}

    public static final String SCHEDULE_APPOINTMENT_FAIULE_MSG = "Was not possible to schedule the appointment";

    public static final String INVALID_TIME_TITLE = "Invalid time requested!";
    public static final String INVALID_OVERLAP_TIME_MSG = "Cannot overlap unavailable time.";
    public static final String INVALID_LUNCH_TIME_MSG = "Cannot schedule on lunch time.";
    public static final String INVALID_OUT_BUSINESS_TIME_MSG = "Schedule should be between 8:00AM and 5:45PM";

    public static final String INVALID_DATE_TITLE = "Invalid date requested!";
    public static final String INVALID_DATE_REQUEST_MSG = "Cannot schedule appointment on the requested day.";

    public static final String SERVICE_ID_NOT_FOUND_TITLE = "Invalid service requested!";
    public static final String SERVICE_ID_NOT_FOUND_MSG = "The service requested does not exists.";

    public static final String UPDATE_DIARY_ERROR_TITLE = "Something was wrong.";
    public static final String UPDATE_DIARY_ERROR_MSG = "Was not possible to update the requested date.";


}
