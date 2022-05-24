package com.academic.ISSProject.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
