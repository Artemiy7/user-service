package com.mongotask.api.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongotask.api.repositories.filter.UserFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum UserFilterType implements UserFilter  {
    @JsonProperty("firstName")
    FIRST_NAME("firstName", (query, requestParam) -> query.addCriteria(Criteria.where("firstName").regex("^" + requestParam))),

    @JsonProperty("lastName")
    LAST_NAME("lastName", (query, requestParam) -> query.addCriteria(Criteria.where("firstName").regex("^" + requestParam))),

    @JsonProperty("email")
    E_MAIL("email", (query, requestParam) -> query.addCriteria(Criteria.where("firstName").regex("^" + requestParam)));
//
//    @JsonProperty("size")
//    SIZE("size", (query, requestParam) -> query.addCriteria(Criteria.where("firstName").regex("^" + requestParam))),
//
//    @JsonProperty("page")
//    PAGE("page", (query, requestParam) -> query.addCriteria(Criteria.where("firstName").regex("^" + requestParam))),
//
//    @JsonProperty("sorting_field")
//    SORT_FIELD("sorting_field", (query, requestParam) -> query..addCriteria(Criteria.where("firstName").regex("^" + requestParam))),
//
//    @JsonProperty("sorting_order")
//    SORT_ORDER("sorting_order", (query, requestParam));

    private static final Map<String, UserFilterType> ENUM_MAP;
    public static final List<UserFilterType> OCCASION_FILTER_TYPE_LIST;
    private String filterType;
    private UserFilter userFilter;

    UserFilterType(String filterType, UserFilter userFilter) {
        this.filterType = filterType;
        this.userFilter = userFilter;
    }

    public String getFilterType() {
        return filterType;
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

    @Override
    public void filterUser(Query searchQuery, String requestParam) {
        userFilter.filterUser(searchQuery, requestParam);
    }
}
