package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface DemoRepository extends CrudRepository<DemoMessage, String> {
    
}
