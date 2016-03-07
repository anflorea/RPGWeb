package ro.academyplus.dto.validators;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by agheboianu on 07.03.2016.
 */
public class PasswordValidator implements ConstraintValidator<MatchFields, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final MatchFields constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
            Object firstObj = wrapper.getPropertyValue(firstFieldName);
            Object secondObj = wrapper.getPropertyValue(secondFieldName);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            // ignore
        }
        return false;
    }
}
