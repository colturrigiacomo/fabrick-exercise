package com.fabrick.exercise.utility;

import com.fabrick.exercise.models.generics.GenericError;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public class ExceptionUtil {

    public static List<GenericError> genericError(String message) {
        return Collections.singletonList(new GenericError("APP001", StringUtils.isNotBlank(message) ? message : "Generic error") );
    }
}
