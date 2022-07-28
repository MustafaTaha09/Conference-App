package com.pluralsight.conferencedemo.controller;

import com.pluralsight.conferencedemo.model.Session;
import com.pluralsight.conferencedemo.model.Speaker;
import com.pluralsight.conferencedemo.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    @PostMapping // the opposite of GET mapping
    public Speaker create(@RequestBody final Speaker speaker){ //When you need to send data from a client (let's say, a browser) to your API, you send it as a request body. A request body is data sent by the client to your API.
        return speakerRepository.saveAndFlush(speaker); //wno't be saved in the repository until u flush it. hence, the save and flush method
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) //there is no deleteMapping in spring. hence, the requestMethod.Delete
    public void delete(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)//because this is a PUT, we sent the whole object to replace the existing one in the db.
    //however, if this was PATCH, we would pass the only attributes needed to be replaced not the whole object.
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        //we need to find the existing session in the db first
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");//beanutils allows us to copy one object to the existing one. the 3rd param is for ignoring this specific coloumn
        return speakerRepository.saveAndFlush(existingSpeaker);


    }
}
