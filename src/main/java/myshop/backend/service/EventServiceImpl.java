package myshop.backend.service;


import myshop.backend.domain.Event;
import myshop.backend.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Event> listAll() {
        return eventMapper.selectAll();
    }
}
