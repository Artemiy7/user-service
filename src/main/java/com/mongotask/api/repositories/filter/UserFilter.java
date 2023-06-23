package com.mongotask.api.repositories.filter;

import com.mongotask.api.util.ApplicationContextProvider;
import org.springframework.data.mongodb.core.query.Query;

public interface UserFilter {

    static UserFilter getBeanByName(String classType) {
        return ApplicationContextProvider.getApplicationContext().getBean(classType, UserFilter.class);
    }

    void filterUser(Query searchQuery, String requestParam);

}
