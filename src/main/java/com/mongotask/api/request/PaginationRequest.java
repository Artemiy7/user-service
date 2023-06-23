package com.mongotask.api.request;

import lombok.*;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaginationRequest {
    private int size;
    private int page;
    private String sortField;
    private Sort.Direction sort = Sort.Direction.ASC;
}
