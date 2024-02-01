package com.iablonski.planner.users.search;

import org.springframework.data.domain.Sort;

public record UserSearchValues (
        String email,
        String username,
        Integer pageNumber,
        Integer pageSize,
        String sortColumn,
        String sortDirection){

    public Sort.Direction getSortDirection() {
        if (sortDirection == null ||
                sortDirection.trim().isEmpty() ||
                sortDirection.trim().equalsIgnoreCase("asc")) return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }
}