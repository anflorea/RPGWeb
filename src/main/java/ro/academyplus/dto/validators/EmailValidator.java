package ro.academyplus.dto.validators;

import org.springframework.beans.factory.annotation.Autowired;
import ro.academyplus.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by agheboianu on 07.03.2016.
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            String email = value;

            return userRepository.findOneByEmail(email) == null;
        } catch (final Exception ignore) {
            // ignore
        }
        return false;
    }
}
