package com.pluralsight.conferencedemo.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;

    @ManyToMany(mappedBy = "speakers") //speakers here refers to the list attribute in the session class called speakers
    //mapping from one side takes alot (Like in session class), however, mapping from the other side just doesnt take alot.
    private List<Session> sessions;

    public Integer getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(Integer speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeaker_bio() {
        return speaker_bio;
    }

    public void setSpeaker_bio(String speaker_bio) {
        this.speaker_bio = speaker_bio;
    }

    private String speaker_bio;

}
