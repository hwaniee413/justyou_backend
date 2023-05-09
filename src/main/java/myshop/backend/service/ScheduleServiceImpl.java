package myshop.backend.service;

import myshop.backend.domain.Schedule;
import myshop.backend.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Override
    public List<Schedule> listByDeptname(String deptname) {
        return scheduleMapper.selectWithDeptByDeptname(deptname);
    }
}
