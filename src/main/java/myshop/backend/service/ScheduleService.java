package myshop.backend.service;

import myshop.backend.domain.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> listByDeptname(String deptname);
}
