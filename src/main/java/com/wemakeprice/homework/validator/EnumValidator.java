package com.wemakeprice.homework.validator;

import com.wemakeprice.homework.validator.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValue, CharSequence> {

    @Override
    public void initialize(EnumValue constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return false;
    }
}
