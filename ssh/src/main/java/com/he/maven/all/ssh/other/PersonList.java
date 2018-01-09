package com.he.maven.all.ssh.other;

import java.util.List;

/**
 * Created by heyanjing on 2018/1/8 15:35.
 */
public class PersonList {
    private List<Person> persons;

    public PersonList() {
    }

    public PersonList(List<Person> persons) {

        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
