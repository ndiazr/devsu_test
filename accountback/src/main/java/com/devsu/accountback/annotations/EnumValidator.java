package com.devsu.accountback.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<Options, String> {

    private Class<? extends Enum<?>> enumClass;
    private String enumValuesMessage;

    @Override
    public void initialize(Options annotation) {
        this.enumClass = annotation.enumClass();
        this.enumValuesMessage = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .anyMatch(name -> name.equals(value));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Invalid value: " + value + ". Valid values are: " + enumValuesMessage)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
