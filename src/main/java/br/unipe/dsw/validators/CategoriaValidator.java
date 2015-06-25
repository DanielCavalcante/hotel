package br.unipe.dsw.validators;

import br.unipe.dsw.entity.Categoria;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CategoriaValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Categoria.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacidade", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valor", "field.required");
    }
}
