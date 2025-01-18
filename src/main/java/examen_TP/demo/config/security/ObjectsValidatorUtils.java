package examen_TP.demo.config.security;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ObjectsValidatorUtils<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public List<String> validate(T objectToValidate) {
        log.trace("Checking errors.");
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
        }
        return  new ArrayList<>();

    }
}
