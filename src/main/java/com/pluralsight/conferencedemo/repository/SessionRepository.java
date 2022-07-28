package com.pluralsight.conferencedemo.repository;

import com.pluralsight.conferencedemo.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    //we use extend here so that we can make use of some of jpaRepo methods like find, update, delete
}
