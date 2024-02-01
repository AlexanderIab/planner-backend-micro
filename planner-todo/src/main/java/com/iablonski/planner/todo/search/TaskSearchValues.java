package com.iablonski.planner.todo.search;

import org.springframework.data.domain.Sort;

import java.util.Calendar;
import java.util.Date;


public record TaskSearchValues (
        String title,
        Byte completed,
        Long userId,
        Long priorityId,
        Long categoryId,
        Date dateFrom,
        Date dateTo,
        Integer pageNumber,
        Integer pageSize,
        String sortColumn,
        String sortDirection){

    public Boolean getCompleted(){
        return completed != null && completed == 1;
    }

    public Date getDateFrom() {
        Date date = null;
        if (dateFrom != null) {
            Calendar calendarFr = Calendar.getInstance();
            calendarFr.setTime(dateFrom);
            calendarFr.set(Calendar.HOUR_OF_DAY, 0);
            calendarFr.set(Calendar.MINUTE, 0);
            calendarFr.set(Calendar.SECOND, 0);
            calendarFr.set(Calendar.MILLISECOND, 1);
            date = calendarFr.getTime();
        }
        return date;
    }

    public Date getDateTo() {
        Date date = null;
        if (dateTo != null) {
            Calendar calendarFr = Calendar.getInstance();
            calendarFr.setTime(dateTo);
            calendarFr.set(Calendar.HOUR_OF_DAY, 23);
            calendarFr.set(Calendar.MINUTE, 59);
            calendarFr.set(Calendar.SECOND, 59);
            calendarFr.set(Calendar.MILLISECOND, 999);
            date = calendarFr.getTime();
        }
        return date;
    }

    public Sort.Direction getSortDirection() {
        if (sortDirection == null ||
                sortDirection.trim().isEmpty() ||
                sortDirection.trim().equalsIgnoreCase("asc")) return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }
}
