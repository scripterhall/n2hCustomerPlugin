package ing.n2h.validator_annotations.n2hBuisnessAnnotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ing.n2h.validator_annotations.n2hValidators.N2hCustomerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = N2hCustomerValidator.class)
public @interface N2hCustomer {

    boolean name() default false;

    boolean firstName() default false;

    boolean lastName() default false;

    boolean CIN() default false;

    boolean email() default false;

    boolean password() default false;

    boolean birthDate() default false;

    boolean gender() default false;

    boolean phone() default false;

    boolean address() default false;

    String message() default "Invalid customer data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
