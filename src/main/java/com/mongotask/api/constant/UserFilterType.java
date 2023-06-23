package com.mongotask.api.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum UserFilterType {
    @JsonProperty("firstName")
    FIRST_NAME("firstName", "firstNameUserFilter"),
    @JsonProperty("lastName")
    LAST_NAME("lastName", "lastNameUserFilter"),
    @JsonProperty("email")
    E_MAIL("email", "emailUserFilter");

    private static final Map<String, UserFilterType> ENUM_MAP;
    public static final List<UserFilterType> OCCASION_FILTER_TYPE_LIST;
    private String filterType;
    private String filterTypeClassName;

    UserFilterType(String filterType, String filterTypeClassName) {
        this.filterType = filterType;
        this.filterTypeClassName = filterTypeClassName;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getFilterTypeClassName() {
        return filterTypeClassName;
    }

    public static List<UserFilterType> getUserFilterTypeList() {
        return OCCASION_FILTER_TYPE_LIST;
    }

    static {
        OCCASION_FILTER_TYPE_LIST = List.of(UserFilterType.values());
    }

    static {
        Map<String, UserFilterType> map = new ConcurrentHashMap<>();
        for (UserFilterType instance : UserFilterType.values()) {
            map.put(String.valueOf(instance.filterType),  instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static UserFilterType getUserFilterTypeByFilterType (String filterType ) {
        return ENUM_MAP.get(filterType);
    }

    @Override
    public String toString() {
        return name();
    }
}
