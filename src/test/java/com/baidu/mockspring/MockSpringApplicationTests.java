package com.baidu.mockspring;

import com.baidu.mockspring.entity.MemoryDatabase;
import com.baidu.mockspring.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockSpringApplicationTests {

    @Test
    public void contextLoads() {
    }

   //@Autowired
    @Resource
    private MemoryDatabase memoryDatabase;
    @Test
    public void memoryDatabase(){
        System.out.println(memoryDatabase.getPersons());
    }
    @Resource
    private ObjectMapper objectMapper;
    @Test
    public void getBeans() throws IOException {
        System.out.println(objectMapper==null);
        String total = objectMapper.writeValueAsString(memoryDatabase.getPersons());
        System.out.println("total: ===>>> "+total);
        List<Person> lists = objectMapper.readValue(total,new TypeReference<List<Person>>(){});
        System.err.println(lists);
    }

    @Test
    public void test02() throws IOException {
       String jsonPerson = objectMapper.writeValueAsString(new Person(){{
           setAge(10);
           setName("Duan");
           setSex(true);
       }});
        Path base = Paths.get(System.getProperty("user.home"));
        Path target = base.resolve("database.txt");
        try (BufferedWriter bf = Files.newBufferedWriter(target)){
            bf.write(jsonPerson);
            bf.flush();
        }
        System.out.println(jsonPerson);
        System.err.println(objectMapper.readValue(jsonPerson, Person.class));
    }
    @Test
    public void test03(){
        System.getProperties().entrySet().stream().forEach(System.out::println);
        System.out.println(System.getProperty("user.home"));
    }
    @Resource
    private ApplicationContext applicationContext;
    @Test
    public void test04(){
        Arrays.asList(applicationContext.getBeanDefinitionNames())
                .stream()
                .filter(x-> !x.contains("org.springframework"))
                .forEach(System.out::println);
    }


    @Test
    public void test001(){
        List<String> names = memoryDatabase.getPersons()
                .stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());
        //System.out.println(names);
        Assert.assertEquals(3,names.size());
        List<Person> persons=names
                .stream()
                .map(name ->{
           return memoryDatabase.getPersons().stream().filter(person -> person.getName().equals(name)).findFirst().get();
        })
                .collect(Collectors.toList());
       // System.out.println(persons);
        Assert.assertThat(persons.size(), CoreMatchers.equalTo(3));
    }
}
