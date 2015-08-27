package be.vdab.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PostcodeReeksVanKleinerDanOfGelijkAanTotValidator.class)
public @interface PostcodeReeksVanKleinerDanOfGelijkAanTot {
    String message() default "{be.vdab.constraints.VanKleinerDanOfGelijkAanTot}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
