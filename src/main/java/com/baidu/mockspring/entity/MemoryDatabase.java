package com.baidu.mockspring.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class MemoryDatabase {
    
    private List<Person> persons= new ArrayList<Person>(5);
    @Value("${lock:true}")
    private Boolean lock;
    
    @PostConstruct
    public void init(){
        persons.add(new Person(){{
            setSex(true);
            setName("lily");
            setAge(18);
        }});
        persons.add(new Person(){{
            setAge(11);
            setName("tommy");
            setSex(false);
        }});
        persons.add(new Person(){{
            setSex(false);
            setName("John");
            setAge(44);
        }});
    }
    
}
