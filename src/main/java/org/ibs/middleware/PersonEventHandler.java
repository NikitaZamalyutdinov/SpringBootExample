package org.ibs.middleware;

import org.ibs.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler()
public class PersonEventHandler {

    @Autowired
    private PersonMessageProducer messageProducer;

    @HandleBeforeCreate
    public void handlePersonSave(Person p) {
        messageProducer.sendUpdatedPerson(p);
    }

    @HandleBeforeDelete
    public void handlePersonDelete(Person p) {
        messageProducer.sendDeletedPerson(p);
    }

    @HandleBeforeSave
    public void handlePersonUpdate(Person p) {
        messageProducer.sendUpdatedPerson(p);
    }
}
