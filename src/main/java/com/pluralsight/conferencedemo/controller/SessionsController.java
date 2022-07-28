package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.model.Session;
import com.pluralsight.conferencedemo.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //this annotation mean that it will be able to respond to outgoing and ingoing JSON payloads
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    @GetMapping//get mapping maps to the http GET request. u write it mainly when u r receving data
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){ //The @PathVariable annotation is used to extract the value from the URI.
        return sessionRepository.getOne(id);
    }

    @PostMapping // the opposite of GET mapping
    public Session create(@RequestBody final Session session){ //When you need to send data from a client (let's say, a browser) to your API, you send it as a request body. A request body is data sent by the client to your API.
        return sessionRepository.saveAndFlush(session); //wno't be saved in the repository until u flush it. hence, the save and flush method
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //there is no deleteMapping in spring. hence, the requestMethod.Delete
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)//because this is a PUT, we sent the whole object to replace the existing one in the db.
    //however, if this was PATCH, we would pass the only attributes needed to be replaced not the whole object.
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //we need to find the existing session in the db first
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");//beanutils allows us to copy one object to the existing one. the 3rd param is for ignoring this specific coloumn
        return sessionRepository.saveAndFlush(existingSession);


    }
}
