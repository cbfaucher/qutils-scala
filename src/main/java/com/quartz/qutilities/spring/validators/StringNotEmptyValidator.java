/*
 * Copyright (c) 2007 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.spring.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * INSERT YOUR COMMENT HERE....
 *
 * @author Christian
 * @since Quartz...
 */
public class StringNotEmptyValidator implements Validator
{
    public StringNotEmptyValidator()
    {
    }

    public boolean supports(Class clazz)
    {
        return clazz == String.class;
    }

    public void validate(Object target, Errors errors)
    {
        final String s = (String) target;

        if (StringUtils.isBlank(s) )
        {
            errors.reject("", "String is empty/null: " + s);
        }
    }
}
