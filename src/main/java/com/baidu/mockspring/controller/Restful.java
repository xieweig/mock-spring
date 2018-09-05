package com.baidu.mockspring.controller;

import com.baidu.mockspring.entity.MemoryDatabase;
import com.baidu.mockspring.entity.Person;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Restful {
    //@RequestMapping(value = "/try1",method = RequestMethod.GET)
    @GetMapping("/try1")
    public Person handle1(){
        return new Person(){{
            setAge(10);
            setName("zhangsan");
            setSex(true);
        }};
    }

    @Resource
    private MemoryDatabase memoryDatabase;
    @PutMapping("/person")
    public Person save(@RequestBody Person person){

        memoryDatabase.getPersons().add(person);

        return person;
    }
    @GetMapping("/persons")
    public List<Person> getAll(){
        return memoryDatabase.getPersons();
    }
    @GetMapping("/person/{personName}")
    public Person getOne(@PathVariable String personName){
        return memoryDatabase.getPersons()
                .stream()
                .filter(person -> person.getName().contains(personName))
                .findFirst().orElseGet(Person::new);
    }




}
