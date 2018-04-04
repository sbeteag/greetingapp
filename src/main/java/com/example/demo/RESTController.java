package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class RESTController {


    @Autowired
    private GreetingDao greetingDao;

    @RequestMapping(value = "/createGreeting", method = RequestMethod.POST)
    public Greeting createGreeting(@RequestBody Greeting g) throws IOException {
        greetingDao.create(g);
        return g;
    }

    @RequestMapping(value = "/getGreeting/{id}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable("id") int id) throws IOException {
        return greetingDao.getById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Greeting deleteGreeting(@PathVariable("id") int id) throws IOException {
        return greetingDao.delete(id);
    }


    @RequestMapping(value = "/updateGreeting/{id}", method = RequestMethod.PUT)
    public Greeting updateGreeting(@PathVariable("id") int id, @RequestBody String newMessage) throws IOException {
        Greeting greeting = new Greeting(id, newMessage);
        greetingDao.update(greeting);

        return greeting;

    }

}
