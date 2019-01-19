package com.mcubillos.urlshortener.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Scope(value = "singleton")
@Component
public class URLValidator {
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    public static boolean validateURL(String url) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher m = pattern.matcher(url);
        return m.matches();
    }
}
