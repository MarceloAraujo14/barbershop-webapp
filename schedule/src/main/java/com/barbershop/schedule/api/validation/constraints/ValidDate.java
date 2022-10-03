package com.barbershop.schedule.api.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.barbershop.schedule.api.validation.AppointmentDate.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {

    String message() default "Invalid date format.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
