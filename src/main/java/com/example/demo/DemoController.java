package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    private static Logger LOG = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoRepository _repo;
    @Autowired JdbcTemplate _template;

    private DemoMessage getByMessageInsecure(String message) {
        String sqlString = "SELECT * FROM messages WHERE message = '" + message + '"';
        DemoMessage m = _template.queryForObject(sqlString, DemoMessage.class);
        LOG.info("DemoMessage-[" + m + "]");
        return m;
    }


    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DemoMessage get(@PathVariable String id) {
        LOG.info("Getting messages for key [" + id + "] from DB");
        Optional<DemoMessage> m = _repo.findById(id);
        LOG.info("DemoMessage-[" + m + "]");
        return m.isEmpty() ? null : m.get();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public List<DemoMessage> getAll() {
        LOG.info("Getting messages from DB");
        List<DemoMessage> messages = new ArrayList<>();
        _repo.findAll().forEach(m -> messages.add(m));
        //try { Thread.sleep(100); } catch (InterruptedException ex) {ex.printStackTrace();}
        LOG.info("Retrieved " + messages.size() + " fortunes");
        return Collections.unmodifiableList(messages);

    }

    @RequestMapping(value = "/messages", method = RequestMethod.PUT, consumes="application/json")
    @ResponseBody
    @CrossOrigin
    public DemoMessage save(@RequestBody DemoMessage input) {
        LOG.info("Saving message [" + input + "] into DB");
        DemoMessage m =_repo.save(input);
        LOG.info("DemoMessage-[" + m + "]");
        return m;
    }

    
}
