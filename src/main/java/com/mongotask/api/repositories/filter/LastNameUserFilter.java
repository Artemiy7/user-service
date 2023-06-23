package com.mongotask.api.repositories.filter;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class LastNameUserFilter implements UserFilter {
    @Override
    public void filterUser(Query searchQuery, String requestParam) {
        searchQuery.addCriteria(Criteria.where("lastName").regex("^" + requestParam));
    }
}
