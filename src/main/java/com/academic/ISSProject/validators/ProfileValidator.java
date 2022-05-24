package com.academic.ISSProject.validators;

import com.academic.ISSProject.domain.Profile;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProfileValidator implements Validator<Profile>{
    @Override
    public void validate(Profile entity) throws ValidatorException {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(entity.getMailAddress());
        if(!matcher.matches())
            throw new ValidatorException("Invalid email");
        Pattern patternTel = Pattern.compile("^\\d{10}$");
        Matcher matcherTel = patternTel.matcher(entity.getPhoneNumber());
        if(!matcherTel.matches()){
            throw new ValidatorException("invalid phone number!");
        }
    }
}
