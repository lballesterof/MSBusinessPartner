package com.TemplateNTT.application.Validation.Interfaces;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.TemplateNTT.application.Validation.Class.Validator;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = Validator.class)
@Documented
public @interface IValidateBase {

	String message() default "El socio de negocio no es valido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}