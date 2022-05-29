package org.bsu.web.lab8.filter;

import javax.servlet.FilterConfig;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ActionsReader {
    private final FilterConfig filterConfig;

    public ActionsReader(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public static ActionsReader getFrom(FilterConfig filterConfig) {
        return new ActionsReader(filterConfig);
    }

    public Set<String> getActions(String name) {
        return Arrays
                .stream(filterConfig.getInitParameter(name).split(";"))
                .map(String::trim)
                .collect(Collectors.toSet());
    }
}