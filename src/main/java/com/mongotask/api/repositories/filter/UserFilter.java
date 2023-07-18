package com.mongotask.api.repositories.filter;

import com.mongotask.api.util.ApplicationContextProvider;
import org.springframework.data.mongodb.core.query.Query;

@FunctionalInterface
public interface UserFilter {

    void filterUser(Query searchQuery, String requestParam);

}
