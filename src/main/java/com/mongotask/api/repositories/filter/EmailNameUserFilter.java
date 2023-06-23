package com.mongotask.api.repositories.filter;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class EmailNameUserFilter implements UserFilter {
    @Override
    public void filterUser(Query searchQuery, String requestParam) {
        searchQuery.addCriteria(Criteria.where("email").regex("^" + requestParam));
    }
}