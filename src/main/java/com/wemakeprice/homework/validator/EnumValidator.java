package com.wemakeprice.homework.validator;

import com.wemakeprice.homework.validator.annotation.EnumValue;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Request의 type 필드를 검증하기 위한 Enum Custom Validator class
 */
public class EnumValidator implements ConstraintValidator<EnumValue, CharSequence> {

    private List<String> allowValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        allowValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        return allowValues.contains(value.toString().toUpperCase());
    }
}
