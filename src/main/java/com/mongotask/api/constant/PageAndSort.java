package com.mongotask.api.constant;

public enum PageAndSort {
    SIZE("size"),
    PAGE("page"),
    SORT_ODER("sort_order"),
    SORT_FIELD("sort_field");


    private String pageAndSortString;

    PageAndSort(String pageAndSortString) {
        this.pageAndSortString = pageAndSortString;
    }

    public String getPageAndSortString() {
        return pageAndSortString;
    }
}
