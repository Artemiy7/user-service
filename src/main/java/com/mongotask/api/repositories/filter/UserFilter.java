package com.mongotask.api.repositories.filter;

import org.springframework.data.mongodb.core.query.Query;

@FunctionalInterface
public interface UserFilter {

    void filterUser(Query searchQuery, String requestParam);

}
