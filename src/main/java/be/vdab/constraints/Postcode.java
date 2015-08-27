package be.vdab.constraints;

import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Range(min = 1000, max = 9999)
public @interface Postcode {
    @OverridesAttribute(constraint = Range.class, name = "message")
    String message() default "{be.vdab.constraints.Postcode}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
